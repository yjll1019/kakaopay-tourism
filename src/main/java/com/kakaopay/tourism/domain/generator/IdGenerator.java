package com.kakaopay.tourism.domain.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.kakaopay.tourism.domain.exception.IdGenerateFailException;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IdGenerator extends IdentifierGenerator {
    @Override
    default Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Logger logger = LoggerFactory.getLogger(IdGenerator.class);

        Serializable id = generatePrimaryKey();
        String query = String.format(getQuery(), id);

        try (Connection connection = session.connection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return generate(session, object);
            }

            logger.info("success {} id generate", this.getClass().getSimpleName());
            return id;
        } catch (SQLException e) {
            logger.error("Exception : {}", e.getMessage());
            throw new IdGenerateFailException(e);
        }
    }

    default String generatePrimaryKey() {
        String uuid = UUID.randomUUID().toString().substring(0, 4);
        return String.format("%s%s%d", getPrefix(), uuid, (Math.random() * 31 * 100));
    }

    String getQuery();

    String getPrefix();
}
