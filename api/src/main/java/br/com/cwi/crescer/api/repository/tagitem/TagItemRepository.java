package br.com.cwi.crescer.api.repository.tagitem;

import br.com.cwi.crescer.api.domain.TagItem;
import org.springframework.data.repository.Repository;

public interface TagItemRepository extends Repository<TagItem, Integer> {
    void save(TagItem tagItem);
}
