package com.sms.core.identity;

import com.sms.core.common.Do;
import com.sms.core.common.FunctionUtils;
import com.sms.core.common.Reader;
import com.sms.core.repositery.IDCardSpecification;
import com.sms.core.repositery.IdCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("idCardService")
@Transactional
public class IDCardServiceImpl implements IDCardService {

    @Override
    public Reader<IdCardRepository, List<IdCardInfo>> search(final IdCardSearchCriteria idCardSearchCriteria) {
        return Reader.of
                (idCardRepository -> Do.of(idCardRepository.findAll(IDCardSpecification.isLongTermCustomer(idCardSearchCriteria)))
                                .then(FunctionUtils
                                        .asList(idCard -> IdCardInfo.toBuilder(idCard).build()))
                                .get()
                );
    }
}