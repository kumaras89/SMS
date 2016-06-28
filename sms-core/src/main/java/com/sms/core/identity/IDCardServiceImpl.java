package com.sms.core.identity;

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
                (idCardRepository -> FList.of(idCardRepository.findAll(IDCardSpecification.isLongTermCustomer(idCardSearchCriteria)))
                                .map(IdCardInfo::build)
                                .get()
                );
    }
}