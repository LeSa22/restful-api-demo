package mockito.test.basic;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.config.Application;
import com.spring.controller.WorkController;
import com.spring.enums.StatusWorkEnum;
import com.spring.model.Work;
import com.spring.repository.WorkRespository;
import com.spring.service.WorkCreateUpdateRequest;
import com.spring.service.WorkService;
import com.spring.utils.Paging;

import antlr.Utils;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(WorkController.class)
public class WorkTestMock {

	@MockBean
	private WorkRespository workRepo;
	
	@Autowired
	private WorkService workService;

	@Mock
	List<String> mockedList;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Mock
    private Pageable pageableMock;

	@Mock
	List<Work> list;
    
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getWorksTest() throws Exception {
//		Pageable pageable = PageRequest.of(0, 10);
//		when(Utils.sort(pageable, any, anyInt(), anyString(), anyString())).thenReturn(pageableMock);
//		Mockito.when(workService.getAll(any(Pageable.class))).thenReturn(pageableMock);		
//		List<Work> listWorkExpect = LongStream.range(1, 10).mapToObj(
//				i -> new Work(i, false, "work " + i, LocalDateTime.now(), LocalDateTime.now(), StatusWorkEnum.PLANING))
//				.collect(Collectors.toList());
//
//		Paging<Work> foundPage = Paging.of(listWorkExpect);
//		Mockito.when(workRepo.findAll()).thenReturn(listWorkExpect);
//		assertEquals(listWorkExpect.size(), workService.getAll(pageableMock));
	}

	@Test
    public void createEmployeeTest()
    {
		Work work1 = new Work(8L, false, "work 1", LocalDateTime.now(), LocalDateTime.now(), StatusWorkEnum.PLANING);
		WorkCreateUpdateRequest workRequest = new WorkCreateUpdateRequest(false, "work 1", LocalDateTime.now(), LocalDateTime.now(), StatusWorkEnum.PLANING);
		Mockito.when(workRepo.save(work1)).thenReturn(work1);
		assertEquals(work1, workService.create(workRequest));
    }
}
