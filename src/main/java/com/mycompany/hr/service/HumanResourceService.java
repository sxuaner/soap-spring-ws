package com.mycompany.hr.service;

import java.util.Date;
import org.jdom.Element;


public interface HumanResourceService {
    Element bookHoliday(Date startDate, Date endDate, String name);
}