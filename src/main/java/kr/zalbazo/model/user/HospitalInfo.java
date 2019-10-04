package kr.zalbazo.model.user;

import java.util.List;

import kr.zalbazo.model.hospital.Label;
import kr.zalbazo.model.pic.AttachFileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
public class HospitalInfo {
	
	private Long hospitalId;
	private String name;
	private String address;
	private String treatStart;
	private String treatEnd;
	private String tel;
	private String info;
	private String userEmail;
	
	private List<Label> label;
    
    private List<AttachFileDTO> attachList;

    private String password;
    private String role;
	
}
