/*
 * Copyright (C) 2014 Nathan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package preferences;

/**
 *
 * @author Nathan
 */
public class PreferencesManager extends Preferences {
    
    public PreferencesManager(boolean spellCheck,
                       boolean rememberOpenShow,
                       boolean autosave,
                       String emailSuffix) {
        super(spellCheck, rememberOpenShow, autosave, emailSuffix);
    }
    /**
     * @param spellCheck the spellCheck to set
     */
    public void setSpellCheck(boolean spellCheck) {
        this.spellCheck = spellCheck;
    }

    /**
     * @param rememberOpenShow the rememberOpenShow to set
     */
    public void setRememberOpenShow(boolean rememberOpenShow) {
        this.rememberOpenShow = rememberOpenShow;
    }

    /**
     * @param autosave the autosave to set
     */
    public void setAutosave(boolean autosave) {
        this.autosave = autosave;
    }

    /**
     * @param emailSuffix the emailSuffix to set
     */
    public void setEmailSuffix(String emailSuffix) {
        this.emailSuffix = emailSuffix;
    }
}
