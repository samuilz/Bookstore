package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.Request;
import com.zahariev.bookstore.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.getAll();
    }

    @Override
    public void delete(Request request) {
        requestRepository.delete(request);
    }

    @Override
    public Request getById(Integer id) {
        return requestRepository.getById(id);
    }
}
