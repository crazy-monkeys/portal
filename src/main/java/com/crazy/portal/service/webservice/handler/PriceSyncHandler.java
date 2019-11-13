package com.crazy.portal.service.webservice.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.crazy.portal.bean.price.BICatalogPrice;
import com.crazy.portal.bean.webservice.AbstractHandler;
import com.crazy.portal.bean.webservice.IHandler;
import com.crazy.portal.bean.webservice.request.PriceSyncRequest;
import com.crazy.portal.bean.webservice.response.PriceSyncResponse;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.price.CatalogBomsPrice;
import com.crazy.portal.entity.price.CatalogPrice;
import com.crazy.portal.service.price.CatalogPriceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName: PriceSyncHandler
 * @Author: God Man Qiu~
 * @Date: 2019/11/8 14:41
 */
@Slf4j
@Component(value = "portal.price.sync")
public class PriceSyncHandler extends AbstractHandler implements IHandler<PriceSyncRequest, PriceSyncResponse> {
    @Resource
    private CatalogPriceService catalogPriceService;
    @Resource
    private CustomerInfoMapper customerInfoMapper;

    @Override
    public PriceSyncResponse process(PriceSyncRequest request) {
        PriceSyncResponse response = new PriceSyncResponse();
        catalogPriceService.deleteAll();
        List<BICatalogPrice> biActualPrices = catalogPriceService.getBIActualPrices();

        biActualPrices.forEach(x->{
            //查询出客户简称
            CustomerInfo customerInfo = customerInfoMapper.selectByOutCode(x.getCustomer_incode());
            String inCustomer = customerInfo == null ? x.getCustomer_incode() : customerInfo.getCustAbbreviation();

            //将客户编码重置为简称
            x.setCustomer_incode(inCustomer);

            String product = x.getProduct();
            String bu = x.getBU();

            if(x.getProduct().equals("RTM7916-31+RPM6743-12")){
                System.out.println(1);
            }
            //查询当前记录是否已经存在
            CatalogPrice catalogPrice = catalogPriceService.findCatalogPrice(product, bu,inCustomer,x.getPDT());

            final Date now = new Date();
            if(Objects.nonNull(catalogPrice)){
                Integer id = catalogPrice.getId();
                JSONArray boms = catalogPrice.getBoms();
                CatalogPrice updateCatalogPrice = this.buildCatalogPrice(x, boms, now);
                updateCatalogPrice.setId(id);
                updateCatalogPrice.setModifyTime(now);
                catalogPriceService.update(updateCatalogPrice);
                return;
            }
            catalogPriceService.insertCatalogPrice(this.buildCatalogPrice(x, new JSONArray(), now));
        });
        return response;
    }

    /**
     * 计算每次记录的boms总价
     * @param boms
     * @return
     */
    private BigDecimal calculateCatalogPrice(JSONArray boms) {
        BigDecimal totalPrice = new BigDecimal(0);
        String jsonStr = JSON.toJSONString(boms);
        List<CatalogBomsPrice> bomsPrices = JSON.parseArray(jsonStr,CatalogBomsPrice.class);

        for(CatalogBomsPrice bomsPrice : bomsPrices){
            BigDecimal totalBomPrice = bomsPrice.getPrice()
                    .multiply(new BigDecimal(bomsPrice.getQty()))
                    .setScale(5,BigDecimal.ROUND_HALF_DOWN);

            totalPrice = totalPrice.add(totalBomPrice);
        }
        return totalPrice;
    }

    /**
     * 构建目录价格对象
     * @param price
     * @param boms
     * @param now
     * @return
     */
    private CatalogPrice buildCatalogPrice(BICatalogPrice price, JSONArray boms, Date now) {
        CatalogPrice catalogPrice = new CatalogPrice();
        catalogPrice.setBu(price.getBU());
        catalogPrice.setPdt(price.getPDT());
        catalogPrice.setSapCode(price.getSap_code());

        //price.getCustomer_incode() 已经被重置为客户简称
        catalogPrice.setInCustomer(price.getCustomer_incode());
        catalogPrice.setProductModel(price.getProduct());
        catalogPrice.setPriceType(price.getPrice_type());
        catalogPrice.setProductType(price.getClass2());
        catalogPrice.setRemark(price.getComments());
        catalogPrice.setPlatform(price.getClass3());

        String effectiveDate = price.getEffective_date();
        if(StringUtils.isNotEmpty(effectiveDate)){
            catalogPrice.setEffectTime(effectiveDate.substring(0,4)+"-"+effectiveDate.substring(4,6)+"-"+effectiveDate.substring(6));
        }
        catalogPrice.setStatus(price.getActive());
        catalogPrice.setCreateTime(now);

        //构建Bom数据
        CatalogBomsPrice currentBom = this.buildBom(price);

        if(boms.isEmpty()){
            boms.add(currentBom);
        }else{
            //如果boms不为空,说明可能需要更新数据
            String bomsStr = JSON.toJSONString(boms);
            boms = this.buildNewBoms(bomsStr, currentBom);
        }
        catalogPrice.setBoms(boms);
        //计算boms总价
        catalogPrice.setCatalogPrice(this.calculateCatalogPrice(boms));
        return catalogPrice;
    }

    /**
     * 组装Boms
     * 检查当前Bom是否已经在库中存在，如果存在，需要更新该bom的价格，否则进行追加
     * @param bomsStr
     * @param currentBom
     * @return
     */
    private JSONArray buildNewBoms(String bomsStr, CatalogBomsPrice currentBom) {
        List<CatalogBomsPrice> bomsPrices = JSON.parseArray(bomsStr,CatalogBomsPrice.class);

        boolean isExists = !(bomsPrices.stream()
                .filter(x -> x.getBomId().equals(currentBom.getBomId()))
                .collect(Collectors.toList()).isEmpty());

        if(isExists){
            //更新价格
            bomsPrices.stream().forEach(x->{
                if(x.getBomId().equals(currentBom.getBomId())){
                    x.setPrice(x.getPrice().add(currentBom.getPrice()));
                    x.setQty(x.getQty() + currentBom.getQty());
                }
            });
        }
        else{
            bomsPrices.add(currentBom);
        }
        return JSONArray.parseArray(JSON.toJSONString(bomsPrices));
    }

    /**
     * 构建bom对象
     * @param x
     * @return
     */
    private CatalogBomsPrice buildBom(BICatalogPrice x) {
        CatalogBomsPrice bom = new CatalogBomsPrice();
        bom.setBomId(x.getBom_id());
        bom.setBomName(x.getBom_name());
        bom.setInCustomer(x.getCustomer_incode());
        bom.setPrice(new BigDecimal(x.getPrice()));
        bom.setQty(Integer.parseInt(x.getQty()));
        return bom;
    }
}
