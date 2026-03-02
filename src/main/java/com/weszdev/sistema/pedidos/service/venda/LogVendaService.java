package com.weszdev.sistema.pedidos.service.venda;

import com.weszdev.sistema.pedidos.model.LogVenda;
import com.weszdev.sistema.pedidos.model.dto.VendaEventDTO;
import com.weszdev.sistema.pedidos.model.mappers.LogVendaMapper;
import com.weszdev.sistema.pedidos.repository.LogVendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogVendaService implements LogService{

    private final LogVendaRepository repository;
    private final LogVendaMapper mapper;

    @Override
    public void incluirLog(VendaEventDTO evento) {
        LogVenda log = mapper.toEntity(evento);
        repository.save(log);
    }
}
