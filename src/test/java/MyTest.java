import com.dgut.ssm.bean.*;
import com.dgut.ssm.dao.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    private SqlSession session;
    private ConfigurableApplicationContext context;

    @BeforeEach
    public void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionFactory factory = context.getBean("sqlSessionFactory", SqlSessionFactory.class);
        session = factory.openSession();
    }

    @AfterEach
    public void close() {
        context.close();
    }
    @Test
    void testAll() {
        try {
            System.out.println(session);
            UserDao goodsDao = session.getMapper(UserDao.class);
            User aa = goodsDao.getUserByName("hxh");
            System.out.println(aa);
        } finally {
            session.close();
        }
    }
    @Test
    void  testUser(){
        GoodsDao goodsDao=session.getMapper(GoodsDao.class);
        List<Goods> allGoods = goodsDao.getAllGoods();
        for (Goods good : allGoods) {
            System.out.println(good);
        }
        session.close();
    }
    @Test
    void testQueryById(){
        GoodsDao goodsDao=session.getMapper(GoodsDao.class);
        Goods goodsById = goodsDao.getGoodsById(1);
        System.out.println(goodsById);
        session.close();
    }
    @Test
    void Update(){
        ReceiptDao goodsDao=session.getMapper(ReceiptDao.class);
        Receipt goods = new Receipt(2009, 0,"深圳",null);
        goodsDao.addReceipt(goods);
        //发货单添加，查找gid+oid 在中间表增加记录eid
        session.close();
    }
    @Test
    void Del(){
        GoodsDao goodsDao=session.getMapper(GoodsDao.class);
        List<Goods> getGoodsByOrderId = goodsDao.getGoodsByOrderId(1);
        for (Goods goods : getGoodsByOrderId) {
            System.out.println(goods);
        }
        session.close();
    }
    @Test
    void add(){
        OrderDao mapper = session.getMapper(OrderDao.class);
        List<Orders> orderByContractId = mapper.getOrderByContractId(1);
        for (Orders orders : orderByContractId) {
            System.out.println(orders);
        }
        session.close();
    }
    @Test
    void query(){
        GoodsDao mapper = session.getMapper(GoodsDao.class);
        Goods goodsInEid = mapper.getGoodsInEid(2016);
        System.out.println(goodsInEid);
        session.close();
    }
    @Test
    void TestHard(){
        ContractDao mapper = session.getMapper(ContractDao.class);
        Contract allContract = mapper.getContractById(1);
        System.out.println(allContract);
        session.close();
    }
    @Test
    void testQgoods(){
        GoodsDao mapper = session.getMapper(GoodsDao.class);
        Goods goodsByContractId = mapper.getNeedNum(2,1);
        System.out.println(goodsByContractId);
        session.close();
    }
    @Test
    void testDelivery(){
        DeliveryDao mapper = session.getMapper(DeliveryDao.class);
        mapper.updateGom(1532);
        session.close();
    }

}
