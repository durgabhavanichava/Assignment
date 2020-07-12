package com.test.SpringBootRestAPIOnetomany;
import com.test.SpringBootRestAPIOnetomany.jpa.PersonRepository;
import com.test.SpringBootRestAPIOnetomany.model.Person;
import com.test.SpringBootRestAPIOnetomany.rest.PersonController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest
{
    @InjectMocks
    PersonController personController;
    @Mock
    PersonRepository personRepository;
    Person person=new Person();
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }
    //getPersonCount
    @Test
    public void testGetPersonCount()
    {
        List<Long> count = new ArrayList<>();
        count.add(new Long(1));
        when(personRepository.countPerson()).thenReturn(count);
        List<Long> count1 = personController.getPersonCount();
        //Assert.assertEquals(true,associatedatatest.isPresent());
        Assert.assertEquals(1,count1.size());

    }

@Test
public void testGetAllPersons()
   {
    person.setFname("test");
    List<Person> listData= new ArrayList<Person>();
    listData.add(person);
    when(personRepository.findAll()).thenReturn(listData);
    List<Person> person1= personController.getAllPersons();
    Assert.assertEquals(true,person1.iterator().hasNext());
    Assert.assertEquals(1,person1.size());
    }
    @Test
    public void testcreatePerson()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Person person=new Person();
        person.setFname("test");
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person testdata = personController.createPerson(person);
        Assert.assertEquals(4,testdata.getFname().length());
    }


}
