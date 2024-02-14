package com.hewen.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.hewen.entity.query.QuestionsQuery;
import com.hewen.entity.po.Questions;
import com.hewen.entity.vo.ResponseVO;
import com.hewen.service.CommentsService;
import com.hewen.service.QuestionsService;
import com.hewen.utils.AliyunOSSUtil;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 
 * 问题信息表 Controller
 * 
 */
@RestController("questionsController")
@RequestMapping("questions")
public class QuestionsController extends ABaseController{

	@Resource
	private QuestionsService questionsService;

	@Resource
	private CommentsService commentService;
	@Resource
	private RedisTemplate<String, List<String>> redisTemplate;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(QuestionsQuery query){
		return getSuccessResponseVO(questionsService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(Questions bean) {
		questionsService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<Questions> listBean) {
		questionsService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Questions> listBean) {
		questionsService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据QuestionId查询对象
	 */
	@GetMapping("getQuestionsByQuestionId")
	public ResponseVO getQuestionsByQuestionId(String questionId) {
		return getSuccessResponseVO(questionsService.getQuestionsByQuestionId(questionId));
	}

	/**
	 * 根据QuestionId修改对象
	 */
	@RequestMapping("updateQuestionsByQuestionId")
	public ResponseVO updateQuestionsByQuestionId(Questions bean,String questionId) {
		questionsService.updateQuestionsByQuestionId(bean,questionId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据QuestionId删除
	 */
	@RequestMapping("deleteQuestionsByQuestionId")
	public ResponseVO deleteQuestionsByQuestionId(String questionId) {
		questionsService.deleteQuestionsByQuestionId(questionId);
		return getSuccessResponseVO(null);
	}

	/**

	 {
	 "userName": "123",
	 "email": "user@example.com",
	 "content": "这里是用户提出的问题内容",
	 "imageUrl": [
	 "http://example.com/image1.jpg",
	 "http://example.com/image2.jpg"
	 ]
	 }


	 {
	 "userName": "123",  //  评论者的用户名
	 "email": "user@example.com",    //  评论者的邮箱
	 "question_id": 789,            //  评论的问题ID
	 "content": "这是对问题的评论",
	 "image_url": [
	 "http://example.com/image1.jpg",
	 "http://example.com/image2.jpg"
	 ],
	 "parent_id": null  // 如果是二级评论，这里应为父评论的ID
	 }


	 */
	@PostMapping("addQuestion")
	public ResponseVO addQusetion(@RequestBody String jsonData) throws IOException, NoSuchAlgorithmException {
		String is = questionsService.addQuestion(jsonData);
		return getSuccessResponseVO(is);
	}

	@PostMapping("addComment")
	public ResponseVO addComment(@RequestBody String jsonData) throws IOException, NoSuchAlgorithmException {
		String is = commentService.addQuestionComments(jsonData);
		return getSuccessResponseVO(is);
	}

	/**
	 * 上传问题图片
	 *
	 * @param images
	 * @return
	 */
	@PostMapping("uploadQuestionImage")
	public ResponseVO uploadQuestionImage(@RequestParam("images") MultipartFile[] images) throws Exception {
		List<String> imageUrls = new ArrayList<>();
		AliyunOSSUtil aliyunOssUtil = new AliyunOSSUtil();
		for (MultipartFile image : images) {
			String url = aliyunOssUtil.uploadFile(image); // 调用图片上传方法
			imageUrls.add(url);
		}
		// 使用redis临时存储图片url数据
		String key = "uploadImages:" + System.currentTimeMillis();
		redisTemplate.opsForValue().set(key, imageUrls, 1, TimeUnit.HOURS);

		// 将图片URL列表直接返回给前端
		return getSuccessResponseVO(imageUrls);
	}

	/**
	 * 获取新问题
	 * @return
	 */
	@GetMapping("getNewQuestion")
	public ResponseVO getNewQuestion() {
		return getSuccessResponseVO(questionsService.getNewQuestions());
	}


	@PostMapping("getQuestionsAndComments")
	public ResponseVO getQuestionsAndComments(@RequestBody String questionId) {
		return getSuccessResponseVO(questionsService.getQuestionsAndComments(questionId));
	}
}