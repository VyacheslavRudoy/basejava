package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume test = new Resume("Григорий Кислин");
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

        contacts.put(ContactType.PHONE_NUMBER, "+7(921) 855-0482 \n");
        contacts.put(ContactType.SKYPE, "grigory.kislin \n");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru \n");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin \n");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin \n");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473 \n");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/ \n");

        Map<SectionType, AbstractSection> section = new EnumMap<>(SectionType.class);
        section.put(SectionType.OBJECTIVE, new SimpleLineSection("Ведущий стажировок и корпоративного" +
                " обучения по Java Web и Enterprise технологиям \n"));

        section.put(SectionType.PERSONAL, new SimpleLineSection("Аналитический склад ума, сильная" +
                " логика, креативность, инициативность. Пурист кода и архитектуры. \n"));


        ArrayList<String> achievements = new ArrayList<>();
        achievements.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей" +
                " спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot" +
                " + Vaadin проект для комплексных DIY смет \n");

        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное " +
                "взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                "Более 3500 выпускников. \n");

        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
                " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. \n");

        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция" +
                " с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке:" +
                " Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей," +
                " интеграция CIFS/SMB java сервера.\n");

        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. \n");

        achievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии" +
                " через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга" +
                " системы по JMX (Jython/ Django). \n");

        achievements.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat," +
                " Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа. \n");

        section.put(SectionType.ACHIEVEMENT, new BulletedLineSection(achievements));


        ArrayList<String> qualification = new ArrayList<>();
        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 \n");

        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce \n");

        qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL," +
                " SQLite, MS SQL, HSQLDB \n");

        qualification.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy \n");

        qualification.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts \n");

        qualification.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC," +
                " Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT)," +
                " Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements). \n");

        qualification.add("Python: Django. \n");

        qualification.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js \n");

        qualification.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka \n");

        qualification.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2," +
                " LDAP, OAuth1, OAuth2, JWT. \n");

        qualification.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix \n");

        qualification.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway," +
                " Nagios, iReport, OpenCmis, Bonita, pgBouncer \n");

        qualification.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования \n");

        qualification.add("Родной русский, английский \"upper intermediate\" \n");

        section.put(SectionType.QUALIFICATIONS, new BulletedLineSection(qualification));


        List<Experience> experience = new ArrayList<>();
        experience.add(new Experience(LocalDate.of(2013, 10, 1), "Java Online Projects",
                "Автор проекта.", "Создание, организация и проведение Java онлайн " +
                "проектов и стажировок. \n"));

        experience.add(new Experience(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1,
                1), "Wrike", "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная " +
                        "аутентификация, авторизация по OAuth1, OAuth2, JWT SSO. \n"));

        experience.add(new Experience(LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10,
                1), "RIT Center", "Java архитектор", "Организация " +
                "процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение" +
                " CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
                "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS," +
                " BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция" +
                " Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin " +
                "development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis," +
                " Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python \n"));

        experience.add(new Experience(LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4,
                1), "Luxoft (Deutsche Bank)", "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
                        "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация " +
                        "RIA-приложения для администрирования, мониторинга и анализа результатов в области " +
                        "алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock," +
                        " Commet, HTML5. \n"));

        experience.add(new Experience(LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12,
                1), "Yota", "Ведущий специалист", "Дизайн и " +
                "имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3," +
                " JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и " +
                "мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS) \n"));

        experience.add(new Experience(LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6,
                1), "Enkata", "Разработчик ПО", "Реализация " +
                "клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного" +
                " J2EE приложения (OLAP, Data mining). \n"));

        experience.add(new Experience(LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2,
                1), "Siemens AG", "Разработчик ПО", "Разработка " +
                "информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе" +
                " Siemens @vantage (Java, Unix). \n"));

        experience.add(new Experience(LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1,
                1), "Alcatel", "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12" +
                        " (CHILL, ASM). \n"));

        section.put(SectionType.EXPERIENCE, new ExperienceSection(experience));


        List<Experience> education = new ArrayList<>();
        education.add(new Experience(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5,
                1), "Coursera", "'Functional Programming Principles in Scala'" +
                " by Martin Odersky", "\n"));

        education.add(new Experience(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4,
                1), "Luxoft", "Курс 'Объектно-ориентированный анализ ИС. " +
                "Концептуальное моделирование на UML.'", "\n"));

        education.add(new Experience(LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4,
                1), "Siemens AG", "3 месяца обучения мобильным IN сетям " +
                "(Берлин)", "\n"));

        education.add(new Experience(LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3,
                1), "Alcatel", "6 месяцев обучения цифровым телефонным " +
                "сетям (Москва)", "\n"));

        education.add(new Experience(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7,
                1), "Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", "Аспирантура (программист С, С++)",
                "\n"));

        education.add(new Experience(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7,
                1), "Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", "Инженер (программист Fortran, C)",
                "\n"));

        education.add(new Experience(LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6,
                1), "Заочная физико-техническая школа при МФТИ", "Закончил" +
                " с отличием", "\n"));

        section.put(SectionType.EDUCATION, new ExperienceSection(education));


        test.setContacts(contacts);
        test.setSection(section);
        System.out.println(test.getContacts());
        System.out.println(test.getSection());
    }
}
