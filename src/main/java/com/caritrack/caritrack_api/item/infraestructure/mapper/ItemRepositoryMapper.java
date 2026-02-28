package com.caritrack.caritrack_api.item.infraestructure.mapper;

import com.caritrack.caritrack_api.item.domain.Item;
import com.caritrack.caritrack_api.item.infraestructure.repository.ItemJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemRepositoryMapper {

    ItemJpa toJpa(Item item);

    Item toDomain(ItemJpa itemJpa);
}