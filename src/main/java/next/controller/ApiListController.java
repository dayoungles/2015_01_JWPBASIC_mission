package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.JdbcQuestionDao;
import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class ApiListController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Question> questions;
		QuestionDao questionDao = JdbcQuestionDao.getInstance();
				
		questions = questionDao.findAll();
		
		ModelAndView mav = jsonView();
		mav.addObject("questions", questions);
		return mav;
		
	}//11번도 만들어 놓았다고 한다. 

}
