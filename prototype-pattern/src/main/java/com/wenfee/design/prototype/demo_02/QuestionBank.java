package com.wenfee.design.prototype.demo_02;

import com.wenfee.design.prototype.common.AnswerQuestion;
import com.wenfee.design.prototype.common.ChoiceQuestion;
import com.wenfee.design.prototype.demo_02.utils.Topic;
import com.wenfee.design.prototype.demo_02.utils.TopicRandomUtil;

import java.util.*;

/**
 * @author Wenfee
 * @date 2022/12/23
 */
public class QuestionBank implements Cloneable {

    /**
     * 考生
     */
    private String candidate;

    /**
     * 考号
     */
    private String number;

    private ArrayList<ChoiceQuestion> choiceQuestionList = new ArrayList<>();
    private ArrayList<AnswerQuestion> answerQuestionList = new ArrayList<>();

    /**
     * 添加选择题
     *
     * @param choiceQuestion {@link ChoiceQuestion}
     * @return
     */
    public QuestionBank append(ChoiceQuestion choiceQuestion) {
        choiceQuestionList.add(choiceQuestion);
        return this;
    }

    /**
     * 添加解答题
     *
     * @param answerQuestion {@link AnswerQuestion}
     * @return
     */
    public QuestionBank append(AnswerQuestion answerQuestion) {
        answerQuestionList.add(answerQuestion);
        return this;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        QuestionBank questionBank = (QuestionBank) super.clone();
        questionBank.choiceQuestionList = (ArrayList<ChoiceQuestion>) choiceQuestionList.clone();
        questionBank.answerQuestionList = (ArrayList<AnswerQuestion>) answerQuestionList.clone();

        // 题目乱序
        Collections.shuffle(questionBank.choiceQuestionList);
        Collections.shuffle(questionBank.answerQuestionList);

        // 每题的答案乱序处理
        List<ChoiceQuestion> choiceQuestionList = questionBank.choiceQuestionList;
        choiceQuestionList.stream().filter(Objects::nonNull)
                .forEach(d -> {
                    Topic random = TopicRandomUtil.random(d.getOption(), d.getKey());
                    d.setOption(random.getOption());
                    d.setKey(random.getKey());
                });
        return questionBank;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {

        StringBuilder detail = new StringBuilder("考生: " + candidate + "\r\n" +
                "考号: " + number + "\r\n" +
                "--------------------------------------------\r\n" +
                "一、选择题" + "\r\n");

        for (int idx = 0; idx < choiceQuestionList.size(); idx++) {
            detail.append("第").append(idx + 1).append("题: ").append(choiceQuestionList.get(idx).getName()).append("\r\n");
            Map<String, String> option = choiceQuestionList.get(idx).getOption();
            for (String key : option.keySet()) {
                detail.append(key).append(": ").append(option.get(key)).append("\r\n");
            }
            detail.append("答案: ").append(choiceQuestionList.get(idx).getKey()).append("\r\n\n");
        }

        detail.append("二、问答题" + "\r\n");

        for (int idx = 0; idx < answerQuestionList.size(); idx++) {
            detail.append("第").append(idx + 1).append("题: ").append(answerQuestionList.get(idx).getName()).append("\r\n");
            detail.append("答案: ").append(answerQuestionList.get(idx).getKey()).append("\r\n\n");
        }

        return detail.toString();
    }
}
