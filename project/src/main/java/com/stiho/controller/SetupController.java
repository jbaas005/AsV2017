package com.stiho.controller;

import com.stiho.model.Complaint;
import com.stiho.model.Customer;
import com.stiho.model.Employee;
import com.stiho.service.ComplaintService;
import com.stiho.service.CustomerService;
import com.stiho.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author IS204_1
 */
@Controller
public class SetupController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ComplaintService complaintService;

    /**
     * database instellen met testwaaren
     *
     * @return
     */
    @RequestMapping(value = "dba", method = GET)
    public @ResponseBody
    String setUp() {
        dbSetup();
        return "Database seeded.";
    }

    private void dbSetup() {
        ArrayList<String> companies = randomCompany();
        ArrayList<String> city = randomCity();
        ArrayList<String> phone = randomPhone();
        ArrayList<String> email = randomEmails();
        ArrayList<String> code = randomCode();
        ArrayList<String> street = randomStreet();
        ArrayList<String> names = randomNames();
        
        List<Employee> employees = new ArrayList<Employee>();
        Employee employee1 = new Employee("1234", "Em Ployee", "employee@stiho.nl");
        employee1.setEmployeePhone(phone.get(ThreadLocalRandom.current().nextInt(phone.size())));
        Employee employee2 = new Employee("1234", "Mede Werker", "werker@stiho.nl");
        employee2.setEmployeePhone(phone.get(ThreadLocalRandom.current().nextInt(phone.size())));
        Employee manager1 = new Employee("1234", "Manager", "mana@stiho.nl");
        manager1.setEmployeePhone(phone.get(ThreadLocalRandom.current().nextInt(phone.size())));
        Employee manager2 = new Employee("1234", "Manager 2", "mana2@stiho.nl");
        manager2.setEmployeePhone(phone.get(ThreadLocalRandom.current().nextInt(phone.size())));
        employee1.setManager(manager1);
        employee2.setManager(manager2);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(manager2);
        employees.add(manager1);

        List<Customer> customers = new ArrayList<Customer>();
        Customer customer1 = new Customer("ITopia", "Duivendrechtsekade 36-38", "1096AH", "Amsterdam", "contact@itopia.nl");
        Customer customer2 = new Customer("KlusNBouw", "Heerlingengracht 8", "2345AB", "Rotterdam", "contact@klusnbouw.nl");
        Customer customer3 = new Customer("Bouwing", "Appeltjesstraat 101", "6273HG", "Almere", "contact@Bouwing.nl");
        customer1.setEmployee(employee1);
        customer2.setEmployee(employee1);
        customer3.setEmployee(employee1);


        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer(
                    companies.get(ThreadLocalRandom.current().nextInt(companies.size())),
                    street.get(ThreadLocalRandom.current().nextInt(street.size())),
                    code.get(ThreadLocalRandom.current().nextInt(code.size())),
                    city.get(ThreadLocalRandom.current().nextInt(city.size())),
                    email.get(ThreadLocalRandom.current().nextInt(email.size()))
            );
            customer.setEmployee(employees.get(ThreadLocalRandom.current().nextInt(employees.size())));
           customers.add(customer);
            
        }

        List<Complaint> complaints = new ArrayList<Complaint>();
        Complaint complaint1 = new Complaint();
        complaint1.setContactEmail("deboer@itopia.nl");
        complaint1.setContactLastName("de Boer");
        complaint1.setContactName("Ronald");
        complaint1.setContactPhone("0123456789");
        complaint1.setCustomer(customer1);
        complaint1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris accumsan sed erat at iaculis. Pellentesque habitant morbi tristique senectus et netus et malesuada. ");
        complaint1.setPriority(5);
        complaint1.setCategory(3);
        complaint1.setTrackingCode("06b9b813-97dd-48c3-b353-c8af827c15e5");
        complaint1.setStatus(Complaint.Status.HANDLED);
        complaints.add(complaint1);
        for (int i = 0; i < 30; i++) {
            Complaint complaint = new Complaint();
            complaint.setContactEmail(email.get(ThreadLocalRandom.current().nextInt(email.size())));
            complaint.setContactLastName(names.get(ThreadLocalRandom.current().nextInt(names.size())));
            complaint.setContactName(names.get(ThreadLocalRandom.current().nextInt(names.size())));
            complaint.setContactPhone(phone.get(ThreadLocalRandom.current().nextInt(phone.size())));
            complaint.setCustomer(customers.get(ThreadLocalRandom.current().nextInt(customers.size())));
            complaint.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris accumsan sed erat at iaculis. Pellentesque habitant morbi tristique senectus et netus et malesuada. ");
            complaint.setPriority(ThreadLocalRandom.current().nextInt(3) + 1);
            complaint.setCategory(ThreadLocalRandom.current().nextInt(6));
            complaint.setStatus(Complaint.Status.valueOf(ThreadLocalRandom.current().nextInt(3)+1));
            complaints.add(complaint);
        }
        
        Complaint.Status.valueOf(1);

        employeeService.storeAllEmployees(employees);
        customerService.storeAllCustomers(customers);
        complaintService.storeAllComplaints(complaints);
    }

    private ArrayList<String> randomCode() {
        ArrayList<String> code = new ArrayList();
        code.add("97650");
        code.add("E3X 5K8");
        code.add("18784");
        code.add("R5W 7C7");
        code.add("5808");
        code.add("61-940");
        code.add("36622-027");
        code.add("9890");
        code.add("1061");
        code.add("05790");
        code.add("Y4C 9Y3");
        code.add("3299");
        code.add("14404-904");
        code.add("A1R 5M4");
        code.add("55321");
        code.add("7078");
        code.add("75044-780");
        code.add("750314");
        code.add("870752");
        code.add("79-840");
        code.add("03393");
        code.add("3732");
        code.add("AG8 4YO");
        code.add("20715");
        code.add("4353");
        code.add("25111");
        code.add("68674");
        code.add("6958");
        code.add("73068");
        code.add("YO1V 8NS");
        code.add("51741");
        code.add("7192");
        code.add("24487");
        code.add("26279");
        code.add("240194");
        code.add("H0J 4P8");
        code.add("27655");
        code.add("6916");
        code.add("32813");
        code.add("58851");
        code.add("88721-615");
        code.add("7526FG");
        code.add("S1T 5N9");
        code.add("772932");
        code.add("89768");
        code.add("30020-126");
        code.add("89084");
        code.add("48384");
        code.add("282968");
        code.add("7959");
        code.add("51516");
        code.add("08666");
        code.add("59-355");
        code.add("82-666");
        code.add("6334");
        code.add("318816");
        code.add("46-507");
        code.add("913381");
        code.add("10-288");
        code.add("45139");
        code.add("6857");
        code.add("82645");
        code.add("73832");
        code.add("599419");
        code.add("19408");
        code.add("GH1 6GU");
        code.add("151261");
        code.add("58904");
        code.add("259417");
        code.add("8630");
        code.add("77220");
        code.add("13-610");
        code.add("1286");
        code.add("66213");
        code.add("98813");
        code.add("37250");
        code.add("48226");
        code.add("26561");
        code.add("4369");
        code.add("89834");
        code.add("51167");
        code.add("03085-811");
        code.add("76552");
        code.add("4724");
        code.add("97681");
        code.add("62585-258");
        code.add("88-765");
        code.add("6096");
        code.add("39616");
        code.add("64536");
        code.add("18871");
        code.add("44675");
        code.add("36418");
        code.add("3905");
        code.add("73484");
        code.add("94503");
        code.add("7921");
        code.add("47367");
        code.add("36349");
        code.add("48411");
        return code;
    }

    private ArrayList<String> randomNames() {
        ArrayList<String> names = new ArrayList();
        names.add("Jacob");
        names.add("Benjamin");
        names.add("Amery");
        names.add("Zahir");
        names.add("Patrick");
        names.add("Guy");
        names.add("Hu");
        names.add("Dillon");
        names.add("Roth");
        names.add("Abbot");
        names.add("Jerome");
        names.add("Aquila");
        names.add("Devin");
        names.add("Timothy");
        names.add("Curran");
        names.add("Oleg");
        names.add("Kyle");
        names.add("Bradley");
        names.add("Lyle");
        names.add("Paul");
        names.add("Michael");
        names.add("Hammett");
        names.add("Colorado");
        names.add("Arthur");
        names.add("Calvin");
        names.add("Kyle");
        names.add("Jamal");
        names.add("Byron");
        names.add("Coby");
        names.add("Matthew");
        names.add("Stephen");
        names.add("Cameron");
        names.add("Isaac");
        names.add("Caleb");
        names.add("Peter");
        names.add("Garrett");
        names.add("Kuame");
        names.add("Beau");
        names.add("Richard");
        names.add("Macaulay");
        names.add("Trevor");
        names.add("Ralph");
        names.add("Darius");
        names.add("Chadwick");
        names.add("Honorato");
        names.add("Kane");
        names.add("Cyrus");
        names.add("Cyrus");
        names.add("Kenneth");
        names.add("Aidan");
        names.add("Paul");
        names.add("Byron");
        names.add("Octavius");
        names.add("Moses");
        names.add("Nolan");
        names.add("Phillip");
        names.add("Valentine");
        names.add("Kieran");
        names.add("Daquan");
        names.add("Brendan");
        names.add("Dieter");
        names.add("Barrett");
        names.add("Myles");
        names.add("William");
        names.add("Rahim");
        names.add("Stone");
        names.add("Charles");
        names.add("Brendan");
        names.add("Drew");
        names.add("Holmes");
        names.add("Amir");
        names.add("Michael");
        names.add("Brett");
        names.add("Callum");
        names.add("Reece");
        names.add("Herrod");
        names.add("Cadman");
        names.add("Ethan");
        names.add("Jerome");
        names.add("Jerome");
        names.add("Avram");
        names.add("Nero");
        names.add("Leroy");
        names.add("Daquan");
        names.add("Zephania");
        names.add("Quinlan");
        names.add("Martin");
        names.add("Salvador");
        names.add("Dolan");
        names.add("Marvin");
        names.add("Tanek");
        names.add("Talon");
        names.add("Aaron");
        names.add("Ignatius");
        names.add("Hu");
        names.add("Flynn");
        names.add("Murphy");
        names.add("Yuli");
        names.add("Logan");
        names.add("Isaiah");
        names.add("Rosalyn");
        names.add("Medge");
        names.add("Ingrid");
        names.add("Emma");
        names.add("Claudia");
        names.add("Eve");
        names.add("Scarlet");
        names.add("Ariel");
        names.add("Keiko");
        names.add("Regina");
        names.add("Sierra");
        names.add("Sierra");
        names.add("Kathleen");
        names.add("Shea");
        names.add("Martha");
        names.add("Clio");
        names.add("Hannah");
        names.add("Ciara");
        names.add("Dacey");
        names.add("Beatrice");
        names.add("Ariana");
        names.add("Rowan");
        names.add("Fatima");
        names.add("Tallulah");
        names.add("Athena");
        names.add("Dara");
        names.add("Jaden");
        names.add("Jolene");
        names.add("Sierra");
        names.add("Whoopi");
        names.add("Lillian");
        names.add("Germane");
        names.add("Keely");
        names.add("Willa");
        names.add("Kitra");
        names.add("Nichole");
        names.add("Juliet");
        names.add("Tatyana");
        names.add("Olga");
        names.add("Lydia");
        names.add("Calista");
        names.add("Oprah");
        names.add("Portia");
        names.add("Nayda");
        names.add("Stacey");
        names.add("Kelsey");
        names.add("Callie");
        names.add("Montana");
        names.add("Breanna");
        names.add("Giselle");
        names.add("Sacha");
        names.add("Claudia");
        names.add("Shafira");
        names.add("Macey");
        names.add("Joy");
        names.add("Kyla");
        names.add("Helen");
        names.add("Kiona");
        names.add("Brooke");
        names.add("Darryl");
        names.add("Regina");
        names.add("Demetria");
        names.add("Jolene");
        names.add("Oprah");
        names.add("Juliet");
        names.add("Kelsie");
        names.add("Josephine");
        names.add("Donna");
        names.add("Whilemina");
        names.add("Bianca");
        names.add("Keely");
        names.add("Sheila");
        names.add("Tamekah");
        names.add("Kirby");
        names.add("Casey");
        names.add("Aileen");
        names.add("Marah");
        names.add("Zia");
        names.add("Kerry");
        names.add("Xantha");
        names.add("Isadora");
        names.add("Marny");
        names.add("Deirdre");
        names.add("Marah");
        names.add("Fatima");
        names.add("Shea");
        names.add("Xyla");
        names.add("Gisela");
        names.add("Ava");
        names.add("Basia");
        names.add("Anika");
        names.add("Ingrid");
        names.add("Taylor");
        names.add("Yvette");
        names.add("Alexis");
        names.add("Bree");
        names.add("Leandra");
        names.add("Justine");
        names.add("Megan");
        names.add("Maris");

        return names;
    }

    private ArrayList<String> randomEmails() {
        ArrayList<String> emails = new ArrayList();
        emails.add("Aenean.egestas.hendrerit@malesuadafames.com");
        emails.add("porttitor.scelerisque@est.edu");
        emails.add("Aenean.gravida@blanditNam.ca");
        emails.add("risus.Nunc@egetipsumSuspendisse.ca");
        emails.add("ut.dolor@vel.edu");
        emails.add("erat.semper@mattis.ca");
        emails.add("adipiscing.ligula.Aenean@commodo.edu");
        emails.add("purus.gravida.sagittis@cursus.net");
        emails.add("diam.vel.arcu@Suspendissealiquetsem.ca");
        emails.add("fringilla.purus@nuncnullavulputate.ca");
        emails.add("varius.ultrices.mauris@tinciduntDonec.org");
        emails.add("ut.nisi.a@tellus.ca");
        emails.add("nec.cursus@et.co.uk");
        emails.add("turpis.vitae@Praesentinterdumligula.net");
        emails.add("neque.Nullam.ut@Nullamfeugiatplacerat.co.uk");
        emails.add("tristique@elitfermentumrisus.co.uk");
        emails.add("quam.dignissim.pharetra@nonummyFuscefermentum.org");
        emails.add("parturient@Duisami.org");
        emails.add("ut@Duis.com");
        emails.add("eros.non.enim@laoreet.org");
        emails.add("montes.nascetur.ridiculus@duiCraspellentesque.com");
        emails.add("neque.non@ultricies.ca");
        emails.add("non.dui.nec@dolorNulla.com");
        emails.add("Quisque.ornare@tortor.ca");
        emails.add("nisi@vestibulumlorem.net");
        emails.add("pede.nonummy.ut@Crasdolordolor.ca");
        emails.add("malesuada.vel@duinec.com");
        emails.add("sem.magna@velitCraslorem.com");
        emails.add("in.lobortis.tellus@urna.net");
        emails.add("feugiat.metus@liberoettristique.net");
        emails.add("imperdiet@elitNulla.edu");
        emails.add("mi@purusNullamscelerisque.net");
        emails.add("sagittis@nisiaodio.org");
        emails.add("ac.turpis.egestas@ullamcorpermagna.net");
        emails.add("erat@urnasuscipit.com");
        emails.add("lorem@velitQuisque.com");
        emails.add("felis.purus@mollis.org");
        emails.add("Nam.tempor@convallisdolor.co.uk");
        emails.add("non.enim.commodo@lacusEtiambibendum.co.uk");
        emails.add("risus.at@adipiscinglobortisrisus.edu");
        emails.add("iaculis.quis.pede@molestieintempus.net");
        emails.add("placerat.augue@pedenecante.edu");
        emails.add("non.leo.Vivamus@loremipsum.ca");
        emails.add("porttitor.tellus@erosnonenim.net");
        emails.add("penatibus@nullaIntegervulputate.co.uk");
        emails.add("vel@netuset.ca");
        emails.add("nec@neque.com");
        emails.add("magna.Suspendisse@necdiamDuis.ca");
        emails.add("Nullam.lobortis.quam@magnanecquam.net");
        emails.add("ut@sed.co.uk");
        emails.add("est.Nunc.laoreet@nectempusscelerisque.net");
        emails.add("ac@idantedictum.edu");
        emails.add("adipiscing.fringilla.porttitor@accumsaninterdumlibero.net");
        emails.add("Nullam.ut.nisi@felisDonectempor.edu");
        emails.add("sapien.Cras.dolor@erosProinultrices.edu");
        emails.add("ipsum.porta@egestasascelerisque.co.uk");
        emails.add("elit.Curabitur@Duisvolutpat.edu");
        emails.add("nec.cursus.a@sitametluctus.ca");
        emails.add("consectetuer@mi.net");
        emails.add("congue@atortor.net");
        emails.add("nec.mollis.vitae@sollicitudinamalesuada.ca");
        emails.add("dui@lacusAliquamrutrum.ca");
        emails.add("arcu@Loremipsum.edu");
        emails.add("tortor.nibh.sit@condimentumDonec.edu");
        emails.add("purus.in@temporbibendumDonec.org");
        emails.add("massa.lobortis@tellusPhasellus.net");
        emails.add("ligula.Nullam@velit.org");
        emails.add("erat.Sed.nunc@commodoauctorvelit.org");
        emails.add("nunc@aliquetProinvelit.edu");
        emails.add("arcu@semsemper.com");
        emails.add("semper@convalliserat.org");
        emails.add("volutpat@ligulatortordictum.edu");
        emails.add("dignissim@vitaenibh.co.uk");
        emails.add("feugiat.metus.sit@tristique.ca");
        emails.add("a.malesuada.id@mauris.ca");
        emails.add("ultricies@Suspendissetristique.net");
        emails.add("Suspendisse.non@estac.edu");
        emails.add("mus@telluslorem.com");
        emails.add("magna.Lorem.ipsum@etultricesposuere.edu");
        emails.add("sapien@lacusEtiambibendum.ca");
        emails.add("a.mi.fringilla@nisiMauris.ca");
        emails.add("velit@Phaselluselitpede.org");
        emails.add("sed.consequat.auctor@vitaeerat.ca");
        emails.add("sodales.nisi@elitEtiam.edu");
        emails.add("eu@magnaDuisdignissim.edu");
        emails.add("tempor@semNullainterdum.co.uk");
        emails.add("ultricies.dignissim@ac.edu");
        emails.add("dictum.augue.malesuada@accumsannequeet.com");
        emails.add("mi@nonlobortis.net");
        emails.add("auctor.ullamcorper.nisl@eudolor.org");
        emails.add("Quisque.fringilla@diamProin.org");
        emails.add("Nullam.scelerisque@semmollis.ca");
        emails.add("Vivamus@venenatisvel.net");
        emails.add("a@sagittislobortis.co.uk");
        emails.add("nec.metus.facilisis@Crasvulputatevelit.net");
        emails.add("iaculis.nec.eleifend@eget.com");
        emails.add("sagittis@nisisemsemper.net");
        emails.add("volutpat.Nulla.facilisis@primisin.co.uk");
        emails.add("Integer.urna.Vivamus@pedePraesenteu.ca");
        emails.add("erat@ullamcorper.co.uk");

        return emails;
    }

    private ArrayList<String> randomPhone() {
        ArrayList<String> phone = new ArrayList();
        phone.add("06 31 80 30 95");
        phone.add("06 44 07 98 67");
        phone.add("06 51 44 01 08");
        phone.add("06 13 21 98 47");
        phone.add("06 42 69 84 17");
        phone.add("06 93 96 14 19");
        phone.add("06 34 94 50 99");
        phone.add("06 93 20 13 32");
        phone.add("06 59 11 65 15");
        phone.add("06 17 90 86 44");
        phone.add("06 15 62 09 81");
        phone.add("06 41 08 34 09");
        phone.add("06 56 47 35 99");
        phone.add("06 68 40 42 20");
        phone.add("06 87 73 09 04");
        phone.add("06 82 55 00 91");
        phone.add("06 55 05 18 63");
        phone.add("06 82 48 98 00");
        phone.add("06 49 94 21 69");
        phone.add("06 50 71 19 75");
        phone.add("06 43 28 82 06");
        phone.add("06 87 63 03 43");
        phone.add("06 21 86 08 19");
        phone.add("06 26 37 69 34");
        phone.add("06 19 09 17 08");
        phone.add("06 05 66 80 45");
        phone.add("06 28 79 51 24");
        phone.add("06 23 23 66 08");
        phone.add("06 31 71 53 58");
        phone.add("06 06 10 58 29");
        phone.add("06 07 41 10 13");
        phone.add("06 17 62 35 21");
        phone.add("06 55 05 73 45");
        phone.add("06 55 20 22 93");
        phone.add("06 80 15 41 09");
        phone.add("06 74 85 79 08");
        phone.add("06 93 80 59 70");
        phone.add("06 47 74 26 19");
        phone.add("06 01 62 30 15");
        phone.add("06 94 53 54 06");
        phone.add("06 35 92 80 18");
        phone.add("06 44 39 63 34");
        phone.add("06 02 32 77 40");
        phone.add("06 07 01 26 11");
        phone.add("06 62 86 02 04");
        phone.add("06 83 07 21 34");
        phone.add("06 41 20 12 29");
        phone.add("06 50 03 78 64");
        phone.add("06 78 85 74 79");
        phone.add("06 45 20 62 80");
        phone.add("06 21 14 03 63");
        phone.add("06 11 30 85 67");
        phone.add("06 49 55 67 32");
        phone.add("06 78 88 79 05");
        phone.add("06 47 09 35 87");
        phone.add("06 24 66 59 85");
        phone.add("06 24 59 65 61");
        phone.add("06 14 86 33 33");
        phone.add("06 77 19 78 44");
        phone.add("06 33 67 08 07");
        phone.add("06 04 74 82 63");
        phone.add("06 72 83 87 64");
        phone.add("06 71 43 60 01");
        phone.add("06 60 32 78 62");
        phone.add("06 95 05 31 70");
        phone.add("06 21 70 92 85");
        phone.add("06 56 43 31 87");
        phone.add("06 56 96 30 04");
        phone.add("06 93 89 55 18");
        phone.add("06 07 30 03 91");
        phone.add("06 04 20 78 84");
        phone.add("06 80 56 46 65");
        phone.add("06 89 81 62 78");
        phone.add("06 30 92 72 11");
        phone.add("06 26 90 86 70");
        phone.add("06 13 82 59 05");
        phone.add("06 38 50 72 37");
        phone.add("06 35 45 97 23");
        phone.add("06 10 65 95 13");
        phone.add("06 82 25 10 84");
        phone.add("06 80 70 13 42");
        phone.add("06 42 81 77 59");
        phone.add("06 52 77 93 56");
        phone.add("06 71 71 51 94");
        phone.add("06 29 65 46 87");
        phone.add("06 19 25 83 87");
        phone.add("06 97 71 66 58");
        phone.add("06 69 60 12 40");
        phone.add("06 77 33 93 90");
        phone.add("06 25 06 44 40");
        phone.add("06 38 33 99 75");
        phone.add("06 89 59 79 48");
        phone.add("06 57 29 42 57");
        phone.add("06 76 32 18 46");
        phone.add("06 58 48 66 00");
        phone.add("06 42 01 15 65");
        phone.add("06 32 27 98 48");
        phone.add("06 27 56 94 38");
        phone.add("06 85 00 75 85");
        phone.add("06 33 83 03 56");
        return phone;
    }

    private ArrayList<String> randomStreet() {
        ArrayList<String> street = new ArrayList();
        street.add("Ap #661-9240 Pretium St.");
        street.add("Ap #649-5831 Non Rd.");
        street.add("P.O. Box 418, 8726 Fusce Street");
        street.add("8768 Nonummy St.");
        street.add("Ap #396-4303 Tempor Ave");
        street.add("Ap #544-9047 Nulla Street");
        street.add("4541 Cras Rd.");
        street.add("Ap #107-6658 Proin Avenue");
        street.add("491-1356 Tempor Avenue");
        street.add("P.O. Box 527, 4002 Eu St.");
        street.add("296-5971 Odio, Rd.");
        street.add("251-8217 Cras Rd.");
        street.add("P.O. Box 779, 988 Diam Avenue");
        street.add("Ap #704-2992 Blandit. Road");
        street.add("963-2933 Magna. Road");
        street.add("P.O. Box 348, 6215 Sed Avenue");
        street.add("Ap #318-5968 Proin Street");
        street.add("P.O. Box 844, 607 Ipsum St.");
        street.add("P.O. Box 962, 8205 Senectus Av.");
        street.add("6225 Aliquet Road");
        street.add("Ap #319-1072 Porttitor St.");
        street.add("4927 Magnis Avenue");
        street.add("Ap #543-796 Commodo Street");
        street.add("910-8013 Mollis Road");
        street.add("785-7347 Luctus Avenue");
        street.add("573-2129 Faucibus Road");
        street.add("P.O. Box 542, 438 Neque Av.");
        street.add("442-1937 Magna. Avenue");
        street.add("P.O. Box 527, 4593 Luctus Road");
        street.add("9450 Pede St.");
        street.add("Ap #791-9264 Ornare Rd.");
        street.add("P.O. Box 869, 8125 Lacus. Avenue");
        street.add("P.O. Box 446, 1123 Libero Street");
        street.add("P.O. Box 846, 7448 Donec Road");
        street.add("P.O. Box 447, 7842 Nam Street");
        street.add("P.O. Box 111, 196 Gravida St.");
        street.add("6020 Lorem Av.");
        street.add("3140 Sodales Street");
        street.add("P.O. Box 896, 322 Purus, Rd.");
        street.add("P.O. Box 496, 8760 Nullam Av.");
        street.add("P.O. Box 368, 5361 Eget Ave");
        street.add("7384 Sit Street");
        street.add("P.O. Box 896, 3263 Erat, St.");
        street.add("219-2100 In Av.");
        street.add("Ap #805-4915 Dui. Ave");
        street.add("P.O. Box 567, 5013 Odio Av.");
        street.add("244-8892 Lectus Av.");
        street.add("Ap #505-8396 Ac Av.");
        street.add("590-4346 Sed Road");
        street.add("P.O. Box 730, 7324 Pretium Rd.");
        street.add("Ap #606-8287 Hendrerit Rd.");
        street.add("P.O. Box 885, 4887 Praesent Av.");
        street.add("Ap #169-9364 Sed Street");
        street.add("786-3564 Sem Rd.");
        street.add("Ap #855-8613 Lectus Road");
        street.add("4640 Curabitur Road");
        street.add("3252 Sit Rd.");
        street.add("4578 Non, St.");
        street.add("7364 Molestie Street");
        street.add("Ap #778-3073 Curae; Av.");
        street.add("P.O. Box 869, 8683 Amet, Rd.");
        street.add("408-7148 Ut Road");
        street.add("P.O. Box 723, 6628 Neque. St.");
        street.add("522 Molestie Rd.");
        street.add("751-8051 Tempor Street");
        street.add("5125 Quam Rd.");
        street.add("Ap #586-989 Et, Road");
        street.add("5261 Convallis St.");
        street.add("Ap #116-3852 Magnis Ave");
        street.add("413-914 Neque. Street");
        street.add("966 Aliquam Rd.");
        street.add("644-3804 Nulla Rd.");
        street.add("1951 Cras Street");
        street.add("Ap #501-5742 Ligula. St.");
        street.add("P.O. Box 715, 7482 Ornare. St.");
        street.add("Ap #764-3184 Convallis Ave");
        street.add("Ap #221-9028 Purus, Av.");
        street.add("794-5034 Consectetuer, Avenue");
        street.add("Ap #782-7907 Placerat, St.");
        street.add("P.O. Box 427, 9346 Metus Street");
        street.add("P.O. Box 818, 1117 Porta Rd.");
        street.add("933-3329 Consectetuer St.");
        street.add("P.O. Box 726, 9817 Mauris. Rd.");
        street.add("6857 Interdum St.");
        street.add("5350 Mauris Av.");
        street.add("Ap #363-9927 Sollicitudin Avenue");
        street.add("9005 Scelerisque Street");
        street.add("494-3176 Nulla Road");
        street.add("797-9084 Libero Av.");
        street.add("Ap #371-2843 A, Ave");
        street.add("Ap #232-4204 Eu, Rd.");
        street.add("P.O. Box 272, 7516 Ornare, Avenue");
        street.add("1110 Aliquam Avenue");
        street.add("997-2045 Quis St.");
        street.add("Ap #354-2812 Eleifend Rd.");
        street.add("P.O. Box 457, 4547 Porttitor Av.");
        street.add("519-3137 Posuere Rd.");
        street.add("P.O. Box 174, 1724 Tellus Rd.");
        street.add("Ap #207-8134 Vel Ave");
        street.add("Ap #480-9790 Vestibulum Ave");

        return street;
    }

    private ArrayList<String> randomCity() {
        ArrayList<String> city = new ArrayList();
        city.add("Faisalabad");
        city.add("Visso");
        city.add("Sovizzo");
        city.add("Yaxley");
        city.add("Hassan");
        city.add("Abohar");
        city.add("Niel-bij-As");
        city.add("Arnesano");
        city.add("Calais");
        city.add("Prenzlau");
        city.add("Verdun");
        city.add("Palagianello");
        city.add("Alençon");
        city.add("Otranto");
        city.add("Gladstone");
        city.add("Fort Saskatchewan");
        city.add("Wieze");
        city.add("Tilly");
        city.add("Merksem");
        city.add("Leicester");
        city.add("Ongole");
        city.add("Coassolo Torinese");
        city.add("Recife");
        city.add("Baisy-Thy");
        city.add("Whyalla");
        city.add("Jupille-sur-Meuse");
        city.add("Heist-aan-Zee");
        city.add("Dover");
        city.add("Fort McPherson");
        city.add("Whitby");
        city.add("Castlegar");
        city.add("LaSalle");
        city.add("Mandela");
        city.add("Cantley");
        city.add("Trivandrum");
        city.add("Francavilla Fontana");
        city.add("Alwar");
        city.add("Juneau");
        city.add("Nelson");
        city.add("Forst");
        city.add("Saint-Remy");
        city.add("Builth Wells");
        city.add("Gagliato");
        city.add("Glauchau");
        city.add("Lagos");
        city.add("Ettelgem");
        city.add("Peterborough");
        city.add("Woodstock");
        city.add("Çeşme");
        city.add("Bellevue");
        city.add("Surrey");
        city.add("Recklinghausen");
        city.add("Assiniboia");
        city.add("St. Paul");
        city.add("Issime");
        city.add("Corvino San Quirico");
        city.add("Bologna");
        city.add("Altmünster");
        city.add("San Isidro de El General");
        city.add("Gold Coast");
        city.add("Sale");
        city.add("Lustin");
        city.add("Tilff");
        city.add("Jabbeke");
        city.add("Biesme-sous-Thuin");
        city.add("Kooigem");
        city.add("Salamanca");
        city.add("Charny");
        city.add("Waiblingen");
        city.add("Saint-Hilarion");
        city.add("Sylvan Lake");
        city.add("Serampore");
        city.add("Anantapur");
        city.add("Giurdignano");
        city.add("Regensburg");
        city.add("Riparbella");
        city.add("Markham");
        city.add("Crieff");
        city.add("Cap-Rouge");
        city.add("Rebecq");
        city.add("Santarém");
        city.add("Dole");
        city.add("Jemappes");
        city.add("Dera Ghazi Khan");
        city.add("Heusden");
        city.add("Posina");
        city.add("Pointe-au-Pic");
        city.add("Tarvisio");
        city.add("Bellevue");
        city.add("Portsmouth");
        city.add("Berlare");
        city.add("Emarèse");
        city.add("Burntisland");
        city.add("Arnesano");
        city.add("Clearwater Municipal District");
        city.add("North Saanich");
        city.add("Brora");
        city.add("Tiel");
        city.add("Utrecht");
        city.add("Springfield");
        return city;
    }

    private ArrayList<String> randomCompany() {
        ArrayList<String> company = new ArrayList();
        company.add("Dignissim Tempor Arcu Consulting");
        company.add("Mi LLC");
        company.add("At Foundation");
        company.add("Ut Nec Urna PC");
        company.add("Eget Metus In Inc.");
        company.add("Fermentum LLP");
        company.add("Parturient PC");
        company.add("In Felis Nulla Company");
        company.add("Eget Metus Corp.");
        company.add("Nec Orci LLP");
        company.add("Eu Ultrices LLP");
        company.add("Ridiculus Mus Proin Inc.");
        company.add("Ante Vivamus Non LLP");
        company.add("Arcu Curabitur Inc.");
        company.add("Mattis Associates");
        company.add("Curae; PC");
        company.add("Donec Corporation");
        company.add("Donec Nibh Quisque Corporation");
        company.add("Ac Limited");
        company.add("Ac Eleifend Vitae Institute");
        company.add("Vulputate Posuere Vulputate PC");
        company.add("Nunc Corp.");
        company.add("Ut Dolor Inc.");
        company.add("Ac Corp.");
        company.add("Posuere Enim Industries");
        company.add("Tincidunt Associates");
        company.add("Amet PC");
        company.add("Mauris PC");
        company.add("Magna Malesuada Associates");
        company.add("Felis Limited");
        company.add("Eu LLC");
        company.add("Pede Suspendisse Dui Institute");
        company.add("Vel Ltd");
        company.add("Porttitor Tellus LLC");
        company.add("Rutrum Associates");
        company.add("Penatibus Corporation");
        company.add("Congue In Scelerisque Corp.");
        company.add("Felis Orci Inc.");
        company.add("Natoque Penatibus Company");
        company.add("Erat Vel Consulting");
        company.add("Ac Arcu Corporation");
        company.add("Proin Eget LLC");
        company.add("Amet Massa Associates");
        company.add("Ligula Consectetuer Consulting");
        company.add("Magna Industries");
        company.add("Lorem Ipsum Dolor PC");
        company.add("Aliquet Sem Corporation");
        company.add("Turpis Egestas Consulting");
        company.add("Nibh Dolor LLP");
        company.add("Sed Limited");
        company.add("Sed Hendrerit A Incorporated");
        company.add("Vestibulum Company");
        company.add("A Neque Corp.");
        company.add("Sed PC");
        company.add("Magnis Dis Parturient Institute");
        company.add("Aenean Egestas Industries");
        company.add("Donec Tempus Lorem Ltd");
        company.add("Curabitur Egestas PC");
        company.add("Feugiat Non Limited");
        company.add("Non Associates");
        company.add("Luctus PC");
        company.add("Quam Quis Incorporated");
        company.add("Lorem Vehicula Et Corp.");
        company.add("In Cursus Incorporated");
        company.add("Elementum Purus Accumsan Corporation");
        company.add("Dolor Egestas Rhoncus Consulting");
        company.add("Neque Ltd");
        company.add("Cras Convallis Convallis Incorporated");
        company.add("Enim Consequat Institute");
        company.add("Curabitur Incorporated");
        company.add("Erat Inc.");
        company.add("Fusce Aliquam Enim Company");
        company.add("Elementum At LLP");
        company.add("Enim Limited");
        company.add("Et Company");
        company.add("Parturient Montes Incorporated");
        company.add("Scelerisque PC");
        company.add("Et Lacinia Vitae Ltd");
        company.add("Sagittis Augue Eu PC");
        company.add("Nisl Nulla Eu Inc.");
        company.add("Magna Suspendisse Tristique LLC");
        company.add("Velit Incorporated");
        company.add("Ut Lacus Nulla Corporation");
        company.add("Conubia Nostra Per Associates");
        company.add("Sit Industries");
        company.add("Nullam Ltd");
        company.add("Dolor Sit Amet LLP");
        company.add("Nec Metus Facilisis Corp.");
        company.add("Vestibulum Foundation");
        company.add("Pretium Institute");
        company.add("Ut Ipsum LLP");
        company.add("Aenean Company");
        company.add("Ullamcorper Incorporated");
        company.add("Neque In Institute");
        company.add("Eros LLC");
        company.add("Consectetuer Rhoncus Foundation");
        company.add("A Feugiat Tellus LLP");
        company.add("Pellentesque A Industries");
        company.add("Cursus Integer Mollis Corp.");
        company.add("Cras Vulputate Velit Consulting");

        return company;
    }
}