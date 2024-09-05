package golang.chat.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

/**
 * 기본 Entity
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	private String createdAt;

	@Column(name = "modified_at", nullable = false)
	@LastModifiedDate
	private String modifiedAt;

	// 날짜 포멧팅
	@PrePersist // 해당 엔티티를 저장하기 전에 실행
	public void onPrePersist() {
		this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
		this.modifiedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	}

	// 업데이트 날짜 포멧팅
	@PreUpdate  // 해당 엔티티를 업데이트하기 전에 실행
	public void onPreUpdate() {
		this.modifiedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	}
}
