package ru.trustworthyq.graphql.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.trustworthyq.graphql.entity.DBSequence;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    public Long getSequenceNumber(String sequence_name) {
        Query query = new Query(Criteria.where("id").is(sequence_name));

        Update update = new Update().inc("seq", 1);

        DBSequence counter = mongoOperations
                .findAndModify(query, update, options().returnNew(true).upsert(true), DBSequence.class);
        log.info("Trying to update the next id value");
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
