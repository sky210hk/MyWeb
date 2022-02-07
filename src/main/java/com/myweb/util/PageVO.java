package com.myweb.util;

public class PageVO {
	
	//멤버변수
	//화면에 그려질 페이지네이션을 계산하는 변수 선언
	private int startPage; //시작 페이지 번호
	private int endPage; //끝 페이지 번호
	private boolean prev, next; //이전, 다음버튼
	
	private int pageNum; //현재 조회하는 페이지
	private int amount; //화면에 보여질 데이터 개수
	private int total; //전체 게시글 수
	private int realEnd; //실제 끝 번호
	
	//생성자 
	//생성될 때, (페이지번호, 데이터개수, 총 게시글 수) 를 받는다.
	public PageVO(int pageNum, int amount, int total) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.total = total;
		
		//1. endPage 계산
		//현재 조회하는 번호가 1 ~ 10 -> 10
		//현재 조회하는 번호가 11 ~ 20 -> 20
		//공식 : (int)Math.ceil(페이지 번호 / 화면에 보여질 페이지네이션 개수) * 화면에 보여질 페이지네이션 개수
		this.endPage = (int)Math.ceil( this.pageNum / (double)10 ) * 10;

		//2. startPage 계산
		//공식 : 끝 페이지 번호 - 화면에 보여질 페이지네이션 개수 + 1;
		this.startPage = this.endPage - 10 + 1;
		
		//3. realEnd 계산(페이지네이션의 실제 끝 번호)
		//만약 게시글이 52개 라면 ? -> 실제 끝 번호는 6
		//만약 게시글이 163개 라면? -> 실제 끝 번호는 17
		//공식 : (int)Math.ceil(전체 게시글 수 / 화면에 뿌려지는 데이터 개수)
		this.realEnd = (int)Math.ceil( this.total / (double)this.amount );
		
		//4. realEnd를 가지고 endPage를 결정
		//예시 : 131개 게시물이 있다고 가정할 때 
		//1번페이지 출력 -> endPage = 10, realEnd = 14 : (10)
		//11번페이지 출력 -> endPage = 20, realEnd = 14 : (14)
		//조건 - endPage가 realEnd보다 큰 경우 endPage를 realEnd로 바꾼다.
		if(this.endPage > this.realEnd) {
			this.endPage = this.realEnd;
		} 
		
		//5. prev버튼 활성화 여부(11, 21 ~~ 부터 true)
		//startPage = 1, 11, 21, 31......
		this.prev = this.startPage > 1;
		
		//6. next버튼 활성화 여부
		//예시 : 131개 게시물
		//1 ~ 10번까지 endPage = 10, realEnd = 14 (true)
		//11 ~ 20번까지 endPage = 14, realEnd = 14 (false)
		this.next = this.realEnd > this.endPage;
		
	}
	//변수가 가진 값을 빠르게 확인
	@Override
	public String toString() {
		return "PageVO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", pageNum=" + pageNum + ", amount=" + amount + ", total=" + total + ", realEnd=" + realEnd + "]";
	}
	//getter, setter
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRealEnd() {
		return realEnd;
	}

	public void setRealEnd(int realEnd) {
		this.realEnd = realEnd;
	}
	
	

}
