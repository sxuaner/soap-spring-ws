package com.mycompany.hr.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.jdom.Element;


@Service                                                                                 
public class StubHumanResourceService implements HumanResourceService {
    // A method stub or simply stub in software development is a piece of code used to stand in for some other programming functionality
    // A stub may simulate the behavior of existing code (such as a procedure on a remote machine, such methods are often called mocks) 
    // or be a temporary substitute for yet-to-be-developed code.
    public Element bookHoliday(Date startDate, Date endDate, String name) {
        return null;
    }
}


// In a unit test, mock objects can simulate the behavior of complex, real objects and are therefore useful when a real object is impractical or impossible to incorporate into a unit test
// E.g a database, which is expensive to be created.