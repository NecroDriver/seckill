package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("seckillList={}",seckillList);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

//    集成测试代码完成逻辑，注意可重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long id = 1001L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 17751312262L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id,phone,md5);
                logger.info("seckillExecution={}",seckillExecution);
            }catch(SeckillCloseException e){
                logger.error(e.getMessage());
            }catch(RepeatKillException e){
                logger.error(e.getMessage());
            }
        }else{
//            秒杀未开启
            logger.warn("exposer={}",exposer);
        }
    }

    @Test
    public void executeSeckillProcedure() throws Exception {
        long seckillId = 1003;
        long phone = 17751312221L;
        String md5 = null;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()){
            md5 = exposer.getMd5();
            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId,phone,md5);
            logger.info(seckillExecution.getStateInfo());
        }
    }
}