package com.sms.core.identity;

import com.sms.core.common.Reader;
import com.sms.core.repositery.IdCardRepository;

import java.util.List;
import java.util.Optional;

public interface IDCardService {

    Reader<IdCardRepository, List<IdCardInfo>> search(final Optional<IdCardSearchCriteria> idCardSearchCriteria);

}
