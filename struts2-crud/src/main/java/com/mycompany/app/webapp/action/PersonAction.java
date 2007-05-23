package com.mycompany.app.webapp.action;

import org.appfuse.service.GenericManager;
import com.mycompany.app.model.Person;
import org.appfuse.webapp.action.BaseAction;

import java.util.List;

public class PersonAction extends BaseAction {
    private GenericManager<Person, Long> personManager;
    private List persons;
    private Person person;
    private Long  id;

    public void setPersonManager(GenericManager<Person, Long> personManager) {
        this.personManager = personManager;
    }

    public List getPersons() {
        return persons;
    }

    public String list() {
        persons = personManager.getAll();
        return SUCCESS;
    }

    public void setId(Long  id) {
        this. id =  id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String delete() {
        personManager.remove(person.getId());
        saveMessage(getText("person.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
            person = personManager.get(id);
        } else {
            person = new Person();
        }

        return SUCCESS;
    }

    public String save() throws Exception {
        if (cancel != null) {
            return "cancel";
        }

        if (delete != null) {
            return delete();
        }

        boolean isNew = (person.getId() == null);

        personManager.save(person);

        String key = (isNew) ? "person.added" : "person.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}