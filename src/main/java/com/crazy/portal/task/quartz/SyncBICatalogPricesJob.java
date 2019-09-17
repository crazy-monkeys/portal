package com.crazy.portal.task.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.crazy.portal.annotation.Task;
import com.crazy.portal.bean.price.BICatalogPrice;
import com.crazy.portal.entity.price.CatalogBomsPrice;
import com.crazy.portal.entity.price.CatalogPrice;
import com.crazy.portal.service.price.CatalogPriceService;
import com.crazy.portal.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:23 2019-09-17
 * @Modified by:
 */
@Slf4j
@Task(value = "同步BI目录价")
@DisallowConcurrentExecution
public class SyncBICatalogPricesJob implements Job {

    @Resource
    private CatalogPriceService catalogPriceService;

    @Override
    public void execute(JobExecutionContext context){
        log.info("-----Start synchronizing bi catalog price data");
        List<BICatalogPrice> biActualPrices = catalogPriceService.getBIActualPrices();
        log.info("-----Synchronize {} pieces of data",biActualPrices.size());

        biActualPrices.forEach(x->{
            final String sapCode = x.getSap_code();
            final Date now = new Date();
            //根据sapCode查询唯一记录
            CatalogPrice catalogPrice = catalogPriceService.selectBySapCode(sapCode);

            if(Objects.nonNull(catalogPrice)){
                String createDate = DateUtil.format(catalogPrice.getCreateTime(),DateUtil.SHORT_FORMAT);
                String nowDate = DateUtil.format(now,DateUtil.SHORT_FORMAT);
                //注意: 由于每次都是全量推送数据,如果先全量删除再全量更新会造成同步时间段内 目录价格服务不可用
                //这里将该风险避免到最低,如果不是当天数据则进行删除,否则进行修改,金额累加
                if(!nowDate.equals(createDate)){
                    log.info("Non - day data are deleted - {}", sapCode);
                    catalogPriceService.delete(catalogPrice.getId());
                }else{
                    log.info("Data of the day are modified - {}", sapCode);
                    JSONArray boms = catalogPrice.getBoms();
                    CatalogPrice updateCatalogPrice = this.buildCatalogPrice(x, boms, now);
                    updateCatalogPrice.setId(catalogPrice.getId());
                    updateCatalogPrice.setModifyTime(new Date());
                    catalogPriceService.update(updateCatalogPrice);
                    return;
                }
            }
            catalogPriceService.insertCatalogPrice(this.buildCatalogPrice(x, new JSONArray(), now));
        });
        log.info("-----End synchronizing bi catalog price data");
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

    private CatalogPrice buildCatalogPrice(BICatalogPrice x, JSONArray boms, Date now) {
        CatalogPrice catalogPrice = new CatalogPrice();
        catalogPrice.setBu(x.getBU());
        catalogPrice.setPdt(x.getPDT());
        catalogPrice.setSapCode(x.getSap_code());
        catalogPrice.setInCustomer(x.getCustomer_incode());
        catalogPrice.setProductModel(x.getProduct());
        catalogPrice.setProductType(x.getPrice_type());
        catalogPrice.setRemark(x.getComments());
        catalogPrice.setPlatform(x.getClass3());
        catalogPrice.setEffectTime(x.getEffective_date());
        catalogPrice.setStatus(x.getActive());
        catalogPrice.setCreateTime(now);
        CatalogBomsPrice bom = new CatalogBomsPrice();
        bom.setBomId(x.getBom_id());
        bom.setBomName(x.getBom_name());
        bom.setInCustomer(x.getCustomer_incode());
        bom.setPrice(new BigDecimal(x.getPrice()));
        bom.setClass2(x.getClass2());
        bom.setQty(Integer.parseInt(x.getQty()));
        boms.add(bom);
        catalogPrice.setBoms(boms);
        //计算boms总价
        catalogPrice.setCatalogPrice(this.calculateCatalogPrice(boms));
        return catalogPrice;
    }
}
