package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustZrAccountTeamMapper;
import com.crazy.portal.entity.cusotmer.CustZrAccountTeam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustZrAccountTeamService
 * @Author: God Man Qiu~
 * @Date: 2019/9/8 17:09
 */
@Slf4j
@Service
public class CustZrAccountTeamService {
    @Resource
    private CustZrAccountTeamMapper custZrAccountTeamMapper;

    public List<CustZrAccountTeam> selectByCustId(Integer custId){
        return custZrAccountTeamMapper.selectByCustId(custId);
    }

    public void saveOrUpdate(List<CustZrAccountTeam> zrAccountTeams, Integer custId, Integer userId){
        if(null == zrAccountTeams || zrAccountTeams.isEmpty()){
            return;
        }
        zrAccountTeams.forEach(e->{
            if(null == e.getId()){
                e.setCustId(custId);
                e.setCreateUserId(userId);
                e.setActive(1);
                custZrAccountTeamMapper.insertSelective(e);
            }else{
                e.setUpdateUserId(userId);
                custZrAccountTeamMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    public void deleteByCustId(List<CustZrAccountTeam> zrAccountTeams, List<CustZrAccountTeam> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == zrAccountTeams || zrAccountTeams.isEmpty()){
                custZrAccountTeamMapper.deleteByCustId(custId);
            }else{
                for(CustZrAccountTeam r : results){
                    Boolean flg = true;
                    for(CustZrAccountTeam c : zrAccountTeams){
                        if(r.getId().equals(c.getId())){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        custZrAccountTeamMapper.deleteByPrimaryKey(r.getId());
                    }
                }
            }
        }
    }

    public void deleteByCustId(Integer custId){
        custZrAccountTeamMapper.deleteByCustId(custId);
    }

    public void save(CustZrAccountTeam record){
        custZrAccountTeamMapper.insertSelective(record);
    }

    /**
     * 销售报备客户
     */
    public void updateTeam(Integer custId, String employeeId){
        if(null != employeeId) {
            CustZrAccountTeam team = new CustZrAccountTeam();
            team.setCustId(custId);
            team.setEmployeeId(employeeId);
            team.setRoleType("142");//142:负责销售 211：客户团队成员 46：销售人员 213：合作伙伴联系人
            team.setActive(1);
            custZrAccountTeamMapper.insertSelective(team);
        }
    }
}
