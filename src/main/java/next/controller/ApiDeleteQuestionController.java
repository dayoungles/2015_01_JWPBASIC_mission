package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.JdbcAnswerDao;
import next.dao.JdbcQuestionDao;
import next.dao.QuestionDao;
import next.model.Question;
import next.service.QnaDelete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class ApiDeleteQuestionController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(AddReplyController.class);
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		logger.debug("questionID", questionId);
		
		//questionId로 question을 찾아온다.
		QuestionDao dao = JdbcQuestionDao.getInstance();
		
		Question question = dao.findById(questionId);
		QnaDelete service = new QnaDelete();
		AnswerDao answerDao = JdbcAnswerDao.getInstance();
		boolean isOthersReply = service.askDelete(question,answerDao);

		if(!isOthersReply){
			dao.deleteQuestion(questionId);
		}
		
		List<Question> questions = dao.findAll();
		ModelAndView mav = jsonView();
		mav.addObject("questions",questions);
		return mav;
	}

}
