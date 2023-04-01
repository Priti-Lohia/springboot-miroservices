package net.javaguidesexample.organizationservice.service;

import net.javaguidesexample.organizationservice.dto.OrganizationDto;
import net.javaguidesexample.organizationservice.entity.Organization;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode(String OrganizationCode);
}
