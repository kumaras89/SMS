package com.sms.core.identity;

import com.sms.core.common.Reader;
import com.sms.core.repositery.IdCardRepository;

import java.util.List;
import java.util.Optional;

public interface IDCardService {

    Reader<IdCardRepository, List<IdCardInfo>> search(final Optional<IdCardSearchCriteria> idCardSearchCriteria);

    Reader<IdCardRepository, Optional<IdCardInfo>> findById(final Long id);

    Reader<IdCardRepository, Optional<IdCardInfo>> update(long id, IdCardInfo idCardInfo);
}
