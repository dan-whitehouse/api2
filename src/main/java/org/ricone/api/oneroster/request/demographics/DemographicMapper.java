package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.StudentEmail;
import org.ricone.api.core.model.StudentRace;
import org.ricone.api.core.model.StudentTelephone;
import org.ricone.api.core.model.view.DemographicView;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component("OneRoster:Demographics:DemographicMapper")
class DemographicMapper {
    DemographicMapper() {
    }

    DemographicsResponse convert(List<DemographicView> instance) {
        List<Demographic> list = new ArrayList<>();
        for (DemographicView wrapper : instance) {
            Demographic demographic = map(wrapper, null);
            if(demographic != null) {
                list.add(demographic);
            }
        }

        DemographicsResponse response = new DemographicsResponse();
        response.setDemographics(list);
        return response;
    }

    DemographicResponse convert(DemographicView wrapper) {
        if(wrapper != null) {
            DemographicResponse response = new DemographicResponse();
            response.setDemographics(map(wrapper, null));
            return response;
        }
        return null;
    }

    private Demographic map(DemographicView instance, String districtId) {
        Demographic demographic = new Demographic();
        demographic.setSourcedId(instance.getSourcedId());
        demographic.setStatus(StatusType.active);
        demographic.setDateLastModified(null);
        demographic.setMetadata(mapMetadata(instance, districtId));

        //Birthdate
        if(instance.getBirthDate() != null) {
            demographic.setBirthDate(instance.getBirthDate().toString());
        }

        //Sex
        demographic.setSex(Gender.valueOf(StringUtils.lowerCase(instance.getSex())));

        //Races
        demographic.setAsian(BooleanUtils.toStringTrueFalse(instance.getAsian()));
        demographic.setBlackOrAfricanAmerican(BooleanUtils.toStringTrueFalse(instance.getBlackOrAfricanAmerican()));
        demographic.setWhite(BooleanUtils.toStringTrueFalse(instance.getWhite()));
        demographic.setAmericanIndianOrAlaskaNative(BooleanUtils.toStringTrueFalse(instance.getAmericanIndianOrAlaskaNative()));
        demographic.setNativeHawaiianOrOtherPacificIslander(BooleanUtils.toStringTrueFalse(instance.getNativeHawaiianOrOtherPacificIslander()));
        demographic.setDemographicRaceTwoOrMoreRaces(BooleanUtils.toStringTrueFalse(instance.getDemographicRaceTwoOrMoreRaces()));
        demographic.setHispanicOrLatinoEthnicity(BooleanUtils.toStringTrueFalse(instance.getHispanicOrLatinoEthnicity()));

        //Of Birth
        demographic.setCountryOfBirthCode(instance.getCountryOfBirthCode());
        demographic.setStateOfBirthAbbreviation(instance.getStateOfBirthAbbreviation());
        demographic.setCityOfBirth(instance.getCityOfBirth());
        demographic.setPublicSchoolResidenceStatus(instance.getPublicSchoolResidenceStatus());

        return demographic;
    }

    private Metadata mapMetadata(DemographicView instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }
}