package mockito.test.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.repository.WorkRespository;

@RunWith(MockitoJUnitRunner.class)
public class WorkTestMock {
	
	@Mock
	private WorkRespository workRepo;
	
	@Mock
	List<String> mockedList;
	
	@Spy
    List<String> list = new ArrayList<>();
	
	@Test
	public void createWorkTest() {
//		mockedList = Mockito.mock(List.class);
//		Mockito.when(mockedList.size()).thenReturn(100);
//		assertEquals(100, mockedList.size());
		list.add("one");
        list.add("two");
        System.out.println(list);
        
//        Mockito.verify(list).add("one");
//        Mockito.verify(list).add("two");
//        Mockito.when(list.size()).thenReturn(100);
        assertEquals(2, list.size());
	}
}
