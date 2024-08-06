package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.hackathon.dto.request.FitnessRequestDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.request.FoodRequestDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.request.WeightRequestDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.response.FitnessResponseDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.response.FoodResponseDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.response.UserInfoResponseDto;
import net.skhu.tastyinventory_be.domain.fitness.Fitness;
import net.skhu.tastyinventory_be.domain.fitness.FitnessRepository;
import net.skhu.tastyinventory_be.domain.food.Food;
import net.skhu.tastyinventory_be.domain.food.FoodRepository;
import net.skhu.tastyinventory_be.domain.user.User;
import net.skhu.tastyinventory_be.domain.weight.Weight;
import net.skhu.tastyinventory_be.domain.weight.WeightRepository;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HackathonService {

    private final FitnessRepository fitnessRepository;
    private final FoodRepository foodRepository;
    private final WeightRepository weightRepository;

    @Transactional
    public void insertFood(FoodRequestDto foodRequestDto, String meal, LocalDate date, User user) {
        Food food = Food.builder()
                .name(foodRequestDto.getName())
                .carbohydrate(foodRequestDto.getCarbohydrate())
                .protein(foodRequestDto.getProtein())
                .fat(foodRequestDto.getFat())
                .meal(meal)
                .date(date)
                .user(user)
                .build();

        foodRepository.save(food);
    }

    public FoodResponseDto checkFood(LocalDate date, User user) {
        List<Food> foodList = foodRepository.findAllByDateAndUser(date, user);

        boolean breakfast = false;
        boolean lunch = false;
        boolean dinner = false;
        boolean snack = false;

        for (Food food : foodList) {
            if (food.getMeal().equalsIgnoreCase("breakfast")) {
                breakfast = true;
            } else if (food.getMeal().equalsIgnoreCase("lunch")) {
                lunch = true;
            } else if (food.getMeal().equalsIgnoreCase("dinner")) {
                dinner = true;
            } else {
                snack = true;
            }
        }

        return FoodResponseDto.of(breakfast, lunch, dinner, snack);
    }

    @Transactional
    public void insertWeight(WeightRequestDto requestDto, LocalDate date, User user) {
        Weight weight = Weight.builder()
                .weight(requestDto.getWeight())
                .date(date)
                .user(user)
                .build();

        weightRepository.save(weight);
    }

    public Integer getWeight(LocalDate date, User user) {
        Weight weight = weightRepository.findByUserAndDate(user, date)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        return weight.getWeight();
    }

    @Transactional
    public void insertFitness(LocalDate date, User user, FitnessRequestDto requestDto) {
        Fitness fitness = Fitness.builder()
                .name(requestDto.getName())
                .minute(requestDto.getMinute())
                .calories(requestDto.getCalories())
                .date(date)
                .user(user)
                .build();

        fitnessRepository.save(fitness);
    }

    public FitnessResponseDto getFitness(LocalDate date, User user) {
        Fitness fitness = fitnessRepository.findByUserAndDate(user, date)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_USER_EXCEPTION,
                        ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));

        return FitnessResponseDto.from(fitness);
    }

    public UserInfoResponseDto getUserInfo(LocalDate date, User user) {
        Integer remainCalories = 66 + (14 * user.getWeight()) + (5 * user.getHeight()) - (7 * user.getAge());
        List<Food> foodList = foodRepository.findAllByDateAndUser(date, user);
        int carbo = 0;
        int protein = 0;
        int fat = 0;

        for (Food food : foodList) {
            carbo += food.getCarbohydrate();
            protein += food.getProtein();
            fat += food.getFat();
        }

        Integer consumedCalories = (protein * 4) + (carbo * 4) + (fat * 9);

        return UserInfoResponseDto.of(
                consumedCalories,
                remainCalories,
                carbo,
                protein,
                fat
        );
    }

}
