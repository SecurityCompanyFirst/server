package com.first951.securitycompanyserver.markplan;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarkPlanServiceImpl implements MarkPlanService {

    private final MarkPlanRepository markPlanRepository;
    private final ModelMapper modelMapper;

    public MarkPlanServiceImpl(MarkPlanRepository markPlanRepository, ModelMapper modelMapper) {
        this.markPlanRepository = markPlanRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public MarkPlanDto get(int id) {
        MarkPlanEntity markPlan = markPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MarkPlan", "id", String.valueOf(id)));

        return modelMapper.map(markPlan, MarkPlanDto.class);
    }

    @Override
    public List<MarkPlanDto> getAll() {
        Iterable<MarkPlanEntity> markPlans = markPlanRepository.findAll();
        List<MarkPlanDto> markPlanDtos = new ArrayList<>();

        markPlans.forEach(markPlan -> modelMapper.map(markPlan, MarkPlanDto.class));
        return markPlanDtos;
    }

    @Override
    public MarkPlanDto create(MarkPlanDto markPlanDto) {
        MarkPlanEntity markPlan = modelMapper.map(markPlanDto, MarkPlanEntity.class);

        MarkPlanEntity createdMarkPlan = markPlanRepository.save(markPlan);
        return modelMapper.map(createdMarkPlan, MarkPlanDto.class);
    }

    @Override
    public MarkPlanDto update(int id, MarkPlanDto markPlanDto) {
        MarkPlanEntity markPlanRequest = modelMapper.map(markPlanDto, MarkPlanEntity.class);
        MarkPlanEntity markPlan = markPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MarkPlan", "id", String.valueOf(id)));
        markPlan.setSchedule(markPlanRequest.getSchedule());
        markPlan.setTimestamp(markPlanRequest.getTimestamp());

        MarkPlanEntity createdMarkPlan = markPlanRepository.save(markPlan);
        return modelMapper.map(createdMarkPlan, MarkPlanDto.class);
    }

    @Override
    public MarkPlanDto deletePost(int id) {
        MarkPlanEntity markPlan = markPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MarkPlan", "id", String.valueOf(id)));

        markPlanRepository.delete(markPlan);
        return modelMapper.map(markPlan, MarkPlanDto.class);
    }
}
