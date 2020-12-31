import com.dgut.ssm.bean.*;
import com.dgut.ssm.dao.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ReceiptDao mapper = session.getMapper(ReceiptDao.class);
        Receipt receipt = new Receipt(111, 0, "location", null,1);
        List<Integer> allSend = mapper.isAllSend(1);
        System.out.println(allSend.toString());
        session.close();

    }
    @Test
    void testQueryById(){
        ContractDao mapper = session.getMapper(ContractDao.class);
        Map map=new HashMap<String,Object>();
map.put("id",null);
map.put("status",null);
map.put("sphone",null);
map.put("signdate",null);
map.put("cphone","13144829823");
        List<Contract> contracts = mapper.queryContractCondition(map);
        for (Contract contract : contracts) {
            System.out.println(contract);
        }
        session.close();
    }
    @Test
    void Update(){
        ReceiptDao goodsDao=session.getMapper(ReceiptDao.class);
        Receipt goods = new Receipt(2009, 0,"深圳",null,1);
        System.out.println(goodsDao.getCidByEid(1311));
        //发货单添加，查找gid+oid 在中间表增加记录eid
        session.close();
    }
    @Test
    void Del(){
        GoodsDao mapper = session.getMapper(GoodsDao.class);
        Goods goods = new Goods(null, "面包2", null,null,null);
        List<Goods> goodsByCondition = mapper.getGoodsByCondition(goods);
        System.out.println(goodsByCondition.toString());
    }
    @Test
    void add(){
        StockDao mapper = session.getMapper(StockDao.class);
        //Stock a=new Stock(1234,"锤子",22,new Date(1608134400000L),"wh");
        //Map<String, Object> numNameByOgid = mapper.getNumNameByOgid(13);
        //System.out.println(numNameByOgid.toString());
        WaitingList waitingList = new WaitingList(null,"面包3",2,11,false);
       mapper.newAStock(waitingList);
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
       Map<String,Object> map=new HashMap<String, Object>();
       map.put("id",792);
       map.put("dsfds",3431);
       map.put("sid",1);
       map.put("cid",2);
       map.put("signdate",new Date(1608825600));
       mapper.updateBaseInfo(map);
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
        StatisticDao mapper = session.getMapper(StatisticDao.class);
        List<String> xGoodsType = mapper.GetXGoodsType();
        System.out.println(xGoodsType.toString());
        session.close();
    }

}
