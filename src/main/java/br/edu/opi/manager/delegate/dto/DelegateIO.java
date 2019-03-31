package br.edu.opi.manager.delegate.dto;

import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.project_patterns.models.user.Profile;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class DelegateIO {

    private ModelMapper modelMapper;

    final Converter<DelegateInput, Delegate> delegateConverter = new Converter<DelegateInput, Delegate>() {

        @Override
        public Delegate convert(MappingContext<DelegateInput, Delegate> context) {
            DelegateInput delegateInput = context.getSource();
            Delegate delegate = new Delegate();
            // @formatter:off
            delegate.setCpf(delegateInput.getCpf());
            delegate.setUsername(delegateInput.getUsername());
            delegate.setPassword(delegateInput.getPassword());
            delegate.setFirstName(delegateInput.getName());
            delegate.setLastName(delegateInput.getName());
            delegate.setProfile(new Profile(delegateInput.getProfileId()));

            return delegate;
            // @formatter:on
        }
    };

    public DelegateIO() {
        modelMapper = new ModelMapper();
        modelMapper.addConverter(delegateConverter);
    }

    public Delegate mapTo(DelegateInput delegateInput) {
        return this.modelMapper.map(delegateInput, Delegate.class);
    }
}
