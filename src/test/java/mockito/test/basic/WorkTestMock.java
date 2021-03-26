package mockito.test.basic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
//import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.config.Application;
import com.spring.controller.WorkController;
import com.spring.enums.StatusWorkEnum;
import com.spring.model.Work;
import com.spring.repository.WorkRespository;
import com.spring.service.WorkCreateUpdateRequest;
import com.spring.service.WorkService;

//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(WorkController.class)
public class WorkTestMock {

    private static final LocalDateTime TIME_NOW = LocalDateTime.now();
	private static final int PAGE_INDEX = 1;
    private static final Long WORK_ID = Long.valueOf(14);
    private static final String WORK_NAME = "Work 1";
    private static final LocalDateTime STARTING_DATE =TIME_NOW;
    private static final LocalDateTime ENDING_DATE = TIME_NOW;
    private static final StatusWorkEnum STATUS_WORK = StatusWorkEnum.PLANING;
    private static final boolean DELETED = false;
    
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
//		when(workService.getAll(any(Pageable.class))).thenReturn(pageableMock);		
//		List<Work> listWorkExpect = LongStream.range(1, 10).mapToObj(
//				i -> new Work(i, false, "work " + i, LocalDateTime.now(), LocalDateTime.now(), StatusWorkEnum.PLANING))
//				.collect(Collectors.toList());
//
//		Paging<Work> foundPage = Paging.of(listWorkExpect);
//		when(workRepo.findAll()).thenReturn(listWorkExpect);
//		assertEquals(listWorkExpect.size(), workService.getAll(pageableMock));
	}

	@Test
    public void createEmployeeTest()
    {
		Work work1 = new Work(WORK_ID, DELETED, WORK_NAME, STARTING_DATE, ENDING_DATE, STATUS_WORK);
		WorkCreateUpdateRequest workRequest = new WorkCreateUpdateRequest(false, "work 1", LocalDateTime.now(), LocalDateTime.now(), StatusWorkEnum.PLANING);
		when(workRepo.save(any(Work.class))).thenReturn(work1);
		
		assertEquals(work1, workService.create(workRequest));
    }
	
	@Test
    public void updateEmployeeTest()
    {
		Work work1 = new Work(WORK_ID, DELETED, WORK_NAME, STARTING_DATE, ENDING_DATE, STATUS_WORK);
		WorkCreateUpdateRequest workRequest = new WorkCreateUpdateRequest(DELETED, WORK_NAME, STARTING_DATE, ENDING_DATE, STATUS_WORK);
		
		when(workService.getById(WORK_ID)).thenReturn(Optional.of(work1));
//        Work returned = workService.update(WORK_ID, workRequest);

//		System.out.println(workRequest.getWorkName() + "===" + returned.getWorkName());
//        verify(workRepo, times(1)).findById(work1.getId());
//        verifyNoMoreInteractions(workRepo);
//        
//        assertWork(workRequest, returned);
//		Mockito.when(workService.update(WORK_ID, workRequest)).thenReturn(work1);
//		assertEquals(work1, workService.update(WORK_ID, workRequest));
    }
	
	private void assertWork(WorkCreateUpdateRequest expected, Work actual) {
        assertEquals(expected.getWorkName(), actual.getWorkName());
        assertEquals(expected.getStartingDate(), expected.getStartingDate());
    }
}
