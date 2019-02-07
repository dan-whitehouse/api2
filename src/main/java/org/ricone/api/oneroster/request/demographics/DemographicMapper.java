package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.StudentEmail;
import org.ricone.api.core.model.StudentRace;
import org.ricone.api.core.model.StudentTelephone;
import org.ricone.api.core.model.view.DemographicView;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component("OneRoster:Demographics:DemographicMapper")
class DemographicMapper extends BaseMapper<DemographicView, Demographic, DemographicsResponse, DemographicResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    DemographicMapper() throws NoSuchMethodException {
        super(DemographicView.class, Demographic.class, DemographicsResponse.class, DemographicResponse.class);
    }

    @Override protected Demographic map(DemographicView instance) {
        Demographic demographic = new Demographic();
        demographic.setSourcedId(instance.getSourcedId());
        demographic.setStatus(StatusType.active);
        demographic.setDateLastModified(null);
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

    @Override protected Metadata mapMetadata(DemographicView instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}