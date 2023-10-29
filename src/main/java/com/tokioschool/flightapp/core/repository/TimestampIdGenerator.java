package com.tokioschool.flightapp.core.repository;

import com.github.f4b6a3.tsid.TsidCreator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import java.lang.reflect.Member;

public class TimestampIdGenerator implements IdentifierGenerator {

    public TimestampIdGenerator(TsidGenerator conf, Member annotatedMember,
                                CustomIdGeneratorCreationContext context) {

    }

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return TsidCreator.getTsid256().toString();
    }
}
