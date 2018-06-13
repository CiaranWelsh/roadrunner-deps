
/** @cond doxygenLibsbmlInternal */

/**
 * @file GroupsConsistencyConstraints.cpp
 * @brief Definition of GroupsConsistencyConstraints.
 * @author SBMLTeam
 *
 * <!--------------------------------------------------------------------------
 * This file is part of libSBML. Please visit http://sbml.org for more
 * information about SBML, and the latest version of libSBML.
 *
 * Copyright (C) 2013-2017 jointly by the following organizations:
 * 1. California Institute of Technology, Pasadena, CA, USA
 * 2. EMBL European Bioinformatics Institute (EMBL-EBI), Hinxton, UK
 * 3. University of Heidelberg, Heidelberg, Germany
 *
 * Copyright (C) 2009-2013 jointly by the following organizations:
 * 1. California Institute of Technology, Pasadena, CA, USA
 * 2. EMBL European Bioinformatics Institute (EMBL-EBI), Hinxton, UK
 *
 * Copyright (C) 2006-2008 by the California Institute of Technology,
 * Pasadena, CA, USA
 *
 * Copyright (C) 2002-2005 jointly by the following organizations:
 * 1. California Institute of Technology, Pasadena, CA, USA
 * 2. Japan Science and Technology Agency, Japan
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation. A copy of the license agreement is provided in the
 * file named "LICENSE.txt" included with this software distribution and also
 * available online as http://sbml.org/software/libsbml/license.html
 * ------------------------------------------------------------------------ -->
 */
#ifndef AddingConstraintsToValidator

#include <sbml/validator/VConstraint.h>

#include <sbml/packages/groups/validator/GroupsSBMLError.h>

#include <sbml/packages/groups/common/GroupsExtensionTypes.h>

#endif /* AddingConstraintsToValidator */

#include <sbml/validator/ConstraintMacros.h>
#include "LOMembersConsistentReferences.h"
#include "GroupCircularReferences.h"

using namespace std;

EXTERN_CONSTRAINT(GroupsLOMembersConsistentReferences, LOMembersConsistentReferences);

EXTERN_CONSTRAINT(GroupsNotCircularReferences, GroupCicularReferences);

START_CONSTRAINT (GroupsMemberAllowedAttributes, Member, member)
{
  bool fail = false;
  bool both = true;

  if (member.isSetIdRef() && member.isSetMetaIdRef())
  {
    fail = true;
  }
  else if (!member.isSetIdRef() && !member.isSetMetaIdRef())
  {
    fail = true;
    both = false;
  }

  msg = "Member";
  if (member.isSetId())
  {
    msg += " with id '";
    msg += member.getId();
    msg += "'";
  }
  if (both == true)
  {
    msg += " has values for both 'idRef' and 'metaIdRef'.";
  }
  else
  {
    msg += " does not reference an object with either both 'idRef' or 'metaIdRef'.";
  }


  inv(fail == false);
}
END_CONSTRAINT


START_CONSTRAINT (GroupsMemberIdRefMustBeSBase, Member, member)
{
  pre (member.isSetIdRef());

  bool fail = false;

  SBase * referent = const_cast<Model*>(&m)->getElementBySId(member.getIdRef());

  msg = "Member";
  if (member.isSetId())
  {
    msg += " with id '";
    msg += member.getId();
    msg += "'";
  }
  msg += " has 'idRef' set to '";
  msg += member.getIdRef();
  msg += "' which is not the id of an SBase object in the model.";
  
  if (referent == NULL)
  {
    fail = true;
  }

  inv(fail == false);
}
END_CONSTRAINT


START_CONSTRAINT (GroupsMemberMetaIdRefMustBeSBase, Member, member)
{
  pre (member.isSetMetaIdRef());

  bool fail = false;

  SBase * referent = const_cast<Model*>(&m)->getElementByMetaId(member.getMetaIdRef());

  msg = "Member";
  if (member.isSetId())
  {
    msg += " with id '";
    msg += member.getId();
    msg += "'";
  }
  msg += " has 'idRef' set to '";
  msg += member.getIdRef();
  msg += "' which is not the id of an SBase object in the model.";
  
  if (referent == NULL)
  {
    fail = true;
  }

  inv(fail == false);
}
END_CONSTRAINT

/** @endcond */

