package net.javaguidesexample.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguidesexample.organizationservice.dto.OrganizationDto;
import net.javaguidesexample.organizationservice.entity.Organization;
import net.javaguidesexample.organizationservice.repository.OrganizationRepository;
import net.javaguidesexample.organizationservice.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;
    private ModelMapper modelMapper;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = modelMapper.map(organizationDto,Organization.class);
        Organization savedOrganization = organizationRepository.save(organization);
        return modelMapper.map(savedOrganization,OrganizationDto.class);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return modelMapper.map(organization, OrganizationDto.class);
    }
}
