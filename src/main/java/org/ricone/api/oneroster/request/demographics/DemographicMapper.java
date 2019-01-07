package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.StudentEmail;
import org.ricone.api.core.model.StudentRace;
import org.ricone.api.core.model.StudentTelephone;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class DemographicMapper {
    public DemographicMapper() {
    }

    DemographicsResponse convert(List<StudentWrapper> instance) {
        List<Demographic> list = new ArrayList<>();
        for (StudentWrapper wrapper : instance) {
            Demographic demographic = map(wrapper.getStudent(), wrapper.getDistrictId());
            if(demographic != null) {
                list.add(demographic);
            }
        }

        DemographicsResponse response = new DemographicsResponse();
        response.setDemographics(list);
        return response;
    }

    DemographicResponse convert(StudentWrapper wrapper) {
        if(wrapper != null) {
            DemographicResponse response = new DemographicResponse();
            response.setDemographics(map(wrapper.getStudent(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private Demographic map(Student instance, String districtId) {
        Demographic demographic = new Demographic();
        demographic.setSourcedId(instance.getStudentRefId());
        demographic.setStatus(StatusType.active);
        demographic.setDateLastModified(null);

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getStudentSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        demographic.setMetadata(metadata);

        //Birthdate
        if(instance.getBirthdate() != null) {
            demographic.setBirthDate(instance.getBirthdate().toString());
        }

        //Sex
        if(StringUtils.equalsIgnoreCase(instance.getSexCode(), "Male")) {
            demographic.setSex(Gender.male);
        }
        else if(StringUtils.equalsIgnoreCase(instance.getSexCode(), "Female")) {
            demographic.setSex(Gender.female);
        }

        //Races
        demographic.setAsian(BooleanUtils.toStringTrueFalse(containsRaceCode(instance.getStudentRaces(), "Asian")));
        demographic.setBlackOrAfricanAmerican(BooleanUtils.toStringTrueFalse(containsRaceCode(instance.getStudentRaces(), "Black or African American")));
        demographic.setWhite(BooleanUtils.toStringTrueFalse(containsRaceCode(instance.getStudentRaces(), "White")));
        demographic.setAmericanIndianOrAlaskaNative(BooleanUtils.toStringTrueFalse(containsRaceCode(instance.getStudentRaces(), "American Indian or Alaska Native")));
        demographic.setNativeHawaiianOrOtherPacificIslander(BooleanUtils.toStringTrueFalse(containsRaceCode(instance.getStudentRaces(), "Native Hawaiian or Other Pacific Islander")));
        demographic.setDemographicRaceTwoOrMoreRaces(null);
        demographic.setHispanicOrLatinoEthnicity(BooleanUtils.toStringTrueFalse(instance.getHispanicLatinoEthnicity()));

        //Of Birth
        demographic.setCountryOfBirthCode(instance.getCountryOfBirth());
        demographic.setStateOfBirthAbbreviation(null);
        demographic.setCityOfBirth(null);
        demographic.setPublicSchoolResidenceStatus(null);

        return demographic;
    }

    public static boolean containsRaceCode(Set<StudentRace> list, String raceCode) {
        for (StudentRace object : list) {
            if (StringUtils.equalsIgnoreCase(object.getRaceCode(), raceCode)) {
                return true;
            }
        }
        return false;
    }
}