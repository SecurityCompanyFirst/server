package com.first951.securitycompanyserver.person;

import java.util.List;

public interface PersonService {

    PersonDto get(int id);

    List<PersonDto> getAll();

    PersonDto create(PersonDto personDto);

    PersonDto update(int id, PersonDto personDto);

    PersonDto deletePost(int id);

}
