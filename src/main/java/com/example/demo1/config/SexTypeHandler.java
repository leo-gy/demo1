package com.example.demo1.config;

import com.example.demo1.enums.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(SexEnum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        if(sexEnum.name().equals("男")){
            preparedStatement.setInt(i,1);
        }else if(sexEnum.name().equals("女")){
            preparedStatement.setInt(i,2);
        }
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int sex=resultSet.getInt(s);
        if(sex==1){
            return SexEnum.男;
        }else{
            return SexEnum.女;
        }
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex=resultSet.getInt(i);
        if(sex==1){
            return SexEnum.男;
        }else{
            return SexEnum.女;
        }
    }

    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex=callableStatement.getInt(i);
        if(sex==1){
            return SexEnum.男;
        }else{
            return SexEnum.女;
        }
    }
}
