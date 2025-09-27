package model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class Student {
	private int id;
	private String name;
	private String className;
	private String loginID;
	private String password;
}