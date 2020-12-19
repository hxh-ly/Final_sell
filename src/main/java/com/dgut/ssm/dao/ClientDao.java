package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Client;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClientDao {
    @Select("select cli.id clientId,name,phone,location from client cli,contract where contract.id=#{id} and contract.cid=cli.id")
    public Client getClientById(@Param("id") int id);
    @Select("select id,name,phone,location from client")
    public List<Client> showAllClient();
    @Insert("Insert into client values(#{id},#{name},#{phone},#{location})")
    public int InsertClient(Client client);

    public List<Client> queryClientCondition(Client client);

    public Integer updateClient(Client client);
}
