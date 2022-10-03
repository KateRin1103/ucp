package by.labworks.ucp.service.impl;

import by.labworks.ucp.dto.PageWrapper;
import by.labworks.ucp.dto.SignUpRequest;
import by.labworks.ucp.dto.UserDTO;
import by.labworks.ucp.dto.mapper.UserMapper;
import by.labworks.ucp.entity.User;
import by.labworks.ucp.exception.ResourceNotFoundException;
import by.labworks.ucp.exception.ServiceException;
import by.labworks.ucp.repository.RoleRepository;
import by.labworks.ucp.repository.UserRepository;
import by.labworks.ucp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {

        if (!(signUpRequest.getPassword().equals(signUpRequest.getPasswordConfirm()))) {
            throw new ServiceException("Пароль не совпал с подтверждённым паролем!");
        }

        UserDTO userDto = new UserDTO();
        userDto.setUsername(signUpRequest.getUsername());
        userDto.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userDto.setEmail(signUpRequest.getEmail());
        userDto.setFirstName(signUpRequest.getFirstName());
        userDto.setLastName(signUpRequest.getLastName());
        userDto.setEmail(signUpRequest.getEmail());
        this.save(userDto);
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDto) {

        userRepository.findByUsername(userDto.getUsername())
                .ifPresent(value -> {
                    throw new ServiceException("Пользователь с таким именем пользователя уже существует!");
                });

        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(value -> {
                    throw new ServiceException("Электронная почта " + value.getEmail() + " занята!");
                });

        User user = userMapper.toEntity(userDto);

        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_CUSTOMER")));
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ServiceException("Пользователь с таким именем пользователя не найден!"));

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void update(UserDTO userDto) {

        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new ServiceException("Пользователь не найден!"));

        if (!user.getEmail().equals(userDto.getEmail())) {
            userRepository.findByEmail(userDto.getEmail())
                    .ifPresent(value -> {
                        throw new ServiceException("Электронная почта " + value.getEmail() + " занята!");
                    });
        }

        UserDTO dto = userMapper.toDto(user);


        dto.setEmail(userDto.getEmail());
        dto.setFirstName(userDto.getFirstName());
        dto.setLastName(userDto.getLastName());

        if (Objects.nonNull(userDto.getRoles())) {
            dto.setRoles(userDto.getRoles());
        }
        if (Objects.nonNull(userDto.getBlocked())) {
            dto.setBlocked(userDto.getBlocked());
        }
        userRepository.save(userMapper.toEntity(dto));
    }

    @Override
    public PageWrapper<UserDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> users = userRepository.findAll(pageable);

        return new PageWrapper<>(userMapper.toDtoList(users.toList()), users.getTotalPages(), users.getTotalElements());
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return userMapper.toDto(user);
    }
}
