package org.bp.lab.simpleblog.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class VisitorData {
	
	List<Long> likedComments = new ArrayList<>();

	public List<Long> getLikedComments() {
		return likedComments;
	}

	public void setComments(List<Long> likedComments) {
		this.likedComments = likedComments;
	}

}
