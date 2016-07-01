package com.sms.core.identity;

import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.common.Reader;
import com.sms.core.repositery.IdCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("idCardService")
@Transactional
public class IDCardServiceImpl implements IDCardService {

    @Override
    public Reader<IdCardRepository, List<IdCardInfo>> search(final Optional<IdCardSearchCriteria> idCardSearchCriteria) {
        return Reader.of
                (idCardRepository -> FList.of(idCardRepository.findAll(IDCardSpecification.idCardRequest(idCardSearchCriteria)))
                                .map(IdCardInfo::build)
                                .get()
                );
    }

    @Override
    public Reader<IdCardRepository, Optional<IdCardInfo>> findById(final Long id) {
        return Reader.of(idCardRepository -> Do.of(idCardRepository.findOne(id))
                .then(IdCardInfo::build)
                .then(Optional::ofNullable)
                .get());
    }

    @Override
    public Reader<IdCardRepository, Optional<IdCardInfo>> update(long id, IdCardInfo idCardInfo) {
        return Reader.of(idCardRepository -> Do.of(idCardInfo)
                .then(IdCard::toBuilder)
                .then(idCardBuilder -> idCardBuilder.on(IdCard::getId).set(id))
                .then(idCardBuilder -> idCardBuilder.build())
                .then(idCardRepository::saveAndFlush)
                .then(IdCardInfo::build)
                .then(Optional::ofNullable)
                .get());
    }
}