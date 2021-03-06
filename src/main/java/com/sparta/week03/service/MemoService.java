package com.sparta.week03.service;

import com.sparta.week03.domain.Memo;
import com.sparta.week03.domain.MemoRepository;
import com.sparta.week03.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(memo.getPassword().equals(requestDto.getPassword())){
            memo.update(requestDto);
            return memo.getId();
        }else{
            throw new RuntimeException("Wrong password");
        }
    }

    @Transactional
    public void delete(Long id, MemoRequestDto requestDto){
        Memo memo = memoRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존제하지 않늗다.")
        );
        if(memo.getPassword().equals(requestDto.getPassword())){
            memoRepository.deleteById(id);
        }else{
            throw new RuntimeException("잘못된 비밀번호 입니다");
        }
    }
}