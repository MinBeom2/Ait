package com.aitserver.community.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
class StudyGroupDummyDataInsertTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Ngrinder 테스트용 스터디 그룹 더미데이터 10만 건 벌크 인서트")
    void insertDummyStudyGroups() {
        Long targetUserId = 1L; // 💡 방장을 담당할 실제 유저 ID
        int totalCount = 100000; // 10만 건

        String sql = "INSERT INTO study_groups (title, description, status, owner_id, created_at, updated_at) " +
                "VALUES (?, ?, 'RECRUITING', ?, NOW(), NOW())";

        jdbcTemplate.batchUpdate(sql, new org.springframework.jdbc.core.BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, "성능 테스트용 스터디 모집 " + i);
                ps.setString(2, "스터디 내용입니다. 열심히 할 사람 구합니다 " + i);
                ps.setLong(3, targetUserId);
            }

            @Override
            public int getBatchSize() {
                return totalCount;
            }
        });

        System.out.println("✅ 스터디 그룹 더미 데이터 10만 건 삽입 완료!");
    }
}