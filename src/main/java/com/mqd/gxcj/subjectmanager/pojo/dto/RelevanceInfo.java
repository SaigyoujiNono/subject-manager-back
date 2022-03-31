package com.mqd.gxcj.subjectmanager.pojo.dto;

import com.mqd.gxcj.subjectmanager.pojo.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 此类用于包装一些不常改动的信息
 */
@Data
@Accessors(chain = true)
public class RelevanceInfo {

    private List<Subject> subjects;
    private List<Role> roles;
    private List<Duty> duties;
    private List<Rank> ranks;
    private List<Education> educations;

    public boolean hasSubject(Integer id){
        return subjects.stream().anyMatch(el->id.equals(el.getId()));
    }

    public boolean hasRole(Integer id) {
        return roles.stream().anyMatch(el->id.equals(el.getId()));
    }

    public boolean hasDuty(Integer id) {
        return duties.stream().anyMatch(el->id.equals(el.getId()));
    }

    public boolean hasRank(Integer id) {
        return ranks.stream().anyMatch(el->id.equals(el.getId()));
    }

    public boolean hasEducation(Integer id) {
        return educations.stream().anyMatch(el->id.equals(el.getId()));
    }

    /**
     * 判断传入的id是否存在
     */
    public boolean hasAll(Integer subjectId, Integer roleId, Integer dutyId, Integer rankId, Integer educationId){
        return subjects.stream().anyMatch(el->subjectId.equals(el.getId())) &&
                roles.stream().anyMatch(el->roleId.equals(el.getId())) &&
                duties.stream().anyMatch(el->dutyId.equals(el.getId())) &&
                ranks.stream().anyMatch(el->rankId.equals(el.getId())) &&
                educations.stream().anyMatch(el->educationId.equals(el.getId()));
    }

}
