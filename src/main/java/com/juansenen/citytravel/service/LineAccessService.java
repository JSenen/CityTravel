package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineAccess;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineAccessService {

    List<LineAccess> findAll();
    Optional<LineAccess> findById(long id);
    LineAccess addAccess(LineAccess lineAccess);
    LineAccess delAccess(long id) throws LineNoFoundException;
    LineAccess modyAccess(long id, LineAccess lineAccess) throws LineNoFoundException;
}
