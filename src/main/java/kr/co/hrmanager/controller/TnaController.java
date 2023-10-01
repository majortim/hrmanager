package kr.co.hrmanager.controller;

import kr.co.hrmanager.domain.tna.Tna;
import kr.co.hrmanager.dto.tna.CreateTnaRequest;
import kr.co.hrmanager.dto.tna.TnaResponse;
import kr.co.hrmanager.service.tna.TnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tna")
public class TnaController {

    private final TnaService tnaService;

    @GetMapping("/{id}")
    public TnaResponse get(@PathVariable Long id) {

        Tna tna = tnaService.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "해당 근태 정보를 찾을 수 없습니다."));

        return buildTnaResponse(tna);
    }

    @PostMapping
    public TnaResponse create(@RequestBody CreateTnaRequest request) {

        Tna tna = tnaService.create(request);

        return buildTnaResponse(tna);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tnaService.deleteById(id);
    }

    private TnaResponse buildTnaResponse(Tna tna) {
        return TnaResponse.builder()
                .tnaId(tna.getTnaId())
                .empName(tna.getEmployee().getEmpName())
                .baseYear(tna.getBaseYear())
                .tnaTy(tna.getTnaTy().getType())
                .startDt(tna.getStartDt())
                .endDt(tna.getEndDt())
                .build();
    }
}
