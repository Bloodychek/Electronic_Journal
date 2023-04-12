package com.stpc2.electronic_journal.beans;

/**
 * Class containing constants
 */
public class Constants {
    public final static String QUERY_BRIGADE_NUMBER = "select calc_brigada_20(sysdate) from dual";
    public final static String QUERY_MULTIPLE_SEARCH = "select e from ElectronicJournal e where e.eventDescription LIKE '%'||:eventDescription||'%' and" +
            " e.eventTime between :eventTime and :recordCreationDate";
    public final static String NOT_FOUNT_BY_ID = "Не найдено записи по id: ";
    public final static int PAGE_SIZE = 10;
}
