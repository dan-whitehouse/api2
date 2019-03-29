package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.oneroster.QDemographic;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component("OneRoster:Demographics:DemographicMapper")
class DemographicMapper extends BaseMapper<QDemographic, Demographic, DemographicsResponse, DemographicResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    DemographicMapper() throws NoSuchMethodException {
        super(QDemographic.class, Demographic.class, DemographicsResponse.class, DemographicResponse.class);
    }

    @Override protected Demographic map(QDemographic instance) {
        Demographic demographic = new Demographic();
        demographic.setSourcedId(instance.getSourcedId());
        demographic.setStatus(StatusType.valueOf(instance.getStatus()));
        demographic.setDateLastModified(instance.getDateLastModified().atZone(ZoneId.systemDefault()));
        demographic.setMetadata(mapMetadata(instance));

        //Birthdate
        if(instance.getBirthDate() != null) {
            demographic.setBirthDate(instance.getBirthDate());
        }

        //Sex
        demographic.setSex(Gender.valueOf(StringUtils.lowerCase(instance.getSex())));

        //Races
        demographic.setAsian(instance.getAsian());
        demographic.setBlackOrAfricanAmerican(instance.getBlackOrAfricanAmerican());
        demographic.setWhite(instance.getWhite());
        demographic.setAmericanIndianOrAlaskaNative(instance.getAmericanIndianOrAlaskaNative());
        demographic.setNativeHawaiianOrOtherPacificIslander(instance.getNativeHawaiianOrOtherPacificIslander());
        demographic.setDemographicRaceTwoOrMoreRaces(instance.getDemographicRaceTwoOrMoreRaces());
        demographic.setHispanicOrLatinoEthnicity(instance.getHispanicOrLatinoEthnicity());

        //Of Birth
        demographic.setCountryOfBirthCode(instance.getCountryOfBirthCode());
        demographic.setStateOfBirthAbbreviation(instance.getStateOfBirthAbbreviation());
        demographic.setCityOfBirth(instance.getCityOfBirth());
        demographic.setPublicSchoolResidenceStatus(instance.getPublicSchoolResidenceStatus());

        return demographic;
    }

    @Override protected Metadata mapMetadata(QDemographic instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}